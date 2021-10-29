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
    private final ObservableList<String> listKindOfTime = FXCollections.observableArrayList();
    private AppConst.RANGE_TIME rangeTime;
    private LocalDate currentTime;

    @FXML
    private JFXComboBox<String> cbKindOfTime;

    @FXML
    private Label lbDateView;

    @FXML
    private ListView<Pane> lvTransactions;

    @FXML
    private HBox hbTime;

    @FXML
    void onActionKindOfTime(ActionEvent event) {

        String s = cbKindOfTime.getValue();
        switch (s) {
            case "All" -> {
                this.setRangeTime(AppConst.RANGE_TIME.ALL);
                hbTime.setVisible(false);
                loadTransactions();
            }
            case "Day" -> {
                this.setRangeTime(AppConst.RANGE_TIME.DAY);
                hbTime.setVisible(true);
                loadTransactions();
            }
            case "Month" -> {
                this.setRangeTime(AppConst.RANGE_TIME.MONTH);
                hbTime.setVisible(true);
                loadTransactions();
            }
            case "Year" -> {
                this.setRangeTime(AppConst.RANGE_TIME.YEAR);
                hbTime.setVisible(true);
                loadTransactions();
            }
        }
    }

    @FXML
    void onAddTransactionClick(MouseEvent event) {
        InputTransactionPopup addSpendingPopup = (InputTransactionPopup) GuiUtils.openPopup(this,"InputTransactionPopup");
    }

    @FXML
    void onClickBackward(MouseEvent event) {
        if (this.currentTime == null) return;
        this.currentTime = TimeUtils.previousTimeWithRange(this.currentTime,this.rangeTime);
        this.reloadTxtTime();
        loadTransactions();
    }

    @FXML
    void onClickForward(MouseEvent event) {
        if (this.currentTime == null) return;
        this.currentTime = TimeUtils.nextTimeWithRange(this.currentTime,this.rangeTime);
        this.reloadTxtTime();
        loadTransactions();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeScene = (HomeScene) this.getParam("parent");
        listKindOfTime.addAll("All","Day","Month","Year");
        cbKindOfTime.setItems(listKindOfTime);
        this.setRangeTime(AppConst.RANGE_TIME.ALL);
        hbTime.setVisible(false);
        cbKindOfTime.getSelectionModel().select("All");
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
        if(this.transactionController != null) {
            lvTransactions.getItems().clear();
            List<Transaction> transactions = listTransactionsFilter(transactionController.getAll());

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

    public void setRangeTime(AppConst.RANGE_TIME rangeTime){
        this.rangeTime = rangeTime;
        if (this.currentTime == null){
            this.currentTime = LocalDate.now();
        }
        Date startTime,endTime;
        this.reloadTxtTime();
    }

    private void reloadTxtTime(){
        String s = switch (this.rangeTime) {
            case DAY -> this.currentTime.toString();
            case MONTH -> String.valueOf(this.currentTime.getMonth()) + String.valueOf(this.currentTime.getYear());
            case YEAR -> String.valueOf(this.currentTime.getYear());
            case ALL -> "";
        };
        this.lbDateView.setText(s);
    }

    public List<Transaction> listTransactionsFilter(List<Transaction> transactions){
        List<Transaction> list = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            LocalDate time = transaction.getTime().toLocalDate();
            if(this.rangeTime.equals(AppConst.RANGE_TIME.ALL)){
                return transactions;
            }else if(this.rangeTime.equals(AppConst.RANGE_TIME.DAY)){
                if(this.currentTime.equals(time)){
                    list.add(transaction);
                }
            }else if(this.rangeTime.equals(AppConst.RANGE_TIME.MONTH)){
                if(this.currentTime.getMonth().equals(time.getMonth())){
                    if(this.currentTime.getYear()== time.getYear()){
                        list.add(transaction);
                    }
                }
            }else if(this.rangeTime.equals(AppConst.RANGE_TIME.YEAR)){
                if(this.currentTime.getYear()== time.getYear()){
                    list.add(transaction);
                }
            }
        }
        return list;
    }
}
