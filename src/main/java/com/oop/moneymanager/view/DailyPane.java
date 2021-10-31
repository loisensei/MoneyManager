package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.controller.TransactionController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.utils.GuiUtils;
import com.oop.moneymanager.utils.TimeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DailyPane extends BasePane{
    private TransactionController transactionController;
    private DatePickerPane datePickerPane;


    @FXML
    private Pane pnDatePicker;

    @FXML
    private ListView<Pane> lvTransactions;


    @FXML
    void onAddTransactionClick(MouseEvent event) {
        InputTransactionPopup addSpendingPopup = (InputTransactionPopup) GuiUtils.openPopup(this,"InputTransactionPopup");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeScene = (HomeScene) this.getParam("parent");
        this.initDatePickerPane();
    }

    @Override
    public void reload(Account account){
        if(this.transactionController==null) {
            this.transactionController = new TransactionController(account);
        }else {
            transactionController.setAccount(account);
        }
        loadTransactions();
    }

    public void onAddTransaction(Transaction transaction){
        transactionController.add(transaction);
        homeScene.updateBalance();
        loadTransactions();
    }

    public void onUpdateTransaction(Transaction transaction){
        homeScene.updateBalance();
        this.transactionController.update(transaction);
    }
    public void onDeleteTransaction(Transaction transaction){
        this.transactionController.delete(transaction);
        homeScene.updateBalance();
        loadTransactions();
    }

    public void loadTransactions(){
        LocalDate currentTime = datePickerPane.getCurrentTime();
        AppConst.RANGE_TIME rangeTime = datePickerPane.getRangeTime();
        if(this.transactionController != null) {
            lvTransactions.getItems().clear();
            List<Transaction> transactions = this.transactionController.listTransactionsFilter(rangeTime,currentTime);

            for (Transaction transaction : transactions) {
                FXMLLoader fxmlLoader = new FXMLLoader(DailyPane.class.getResource("ItemTransaction.fxml"));
                try {
                    lvTransactions.getItems().add(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ItemTransaction item = fxmlLoader.getController();
                item.setTransaction(transaction);
                item.setDailyPane(this);

            }
        }
    }

    private void initDatePickerPane(){
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource("DatePickerPane.fxml"));
        try {
            pnDatePicker.getChildren().add(fxmlLoader.load());
            System.out.println("save dpp");
            this.datePickerPane = fxmlLoader.getController();
            System.out.println(this.datePickerPane);
            this.datePickerPane.setChangeTimeListener(this::loadTransactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
