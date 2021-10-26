package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.controller.TransactionController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.utils.GuiUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DailyPane extends BasePane{
    private TransactionController transactionController;
    private final ObservableList<String> listKindOfTime = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<String> cbKindOfTime;

    @FXML
    private Label lbDateView;

    @FXML
    private ListView<Pane> lvTransactions;

    @FXML
    void onActionKindOfTime(ActionEvent event) {

    }

    @FXML
    void onAddTransactionClick(MouseEvent event) {
        InputTransactionPopup addSpendingPopup = (InputTransactionPopup) GuiUtils.openPopup(this,"InputTransactionPopup");
    }

    @FXML
    void onClickBackward(MouseEvent event) {

    }

    @FXML
    void onClickForward(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeScene = (HomeScene) this.getParam("parent");
        listKindOfTime.addAll("Day","Month","Year");
        cbKindOfTime.setItems(listKindOfTime);
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

    public void onUpdateTransaction(){
        homeScene.updateBalance();
    }

    public void loadTransactions(){
        if(this.transactionController != null) {
            lvTransactions.getItems().clear();
            List<Transaction> transactions = transactionController.getAll();
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
}
