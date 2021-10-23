package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.utils.GuiUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScene extends BaseView {
    private AccountController accountController;

    @FXML
    private JFXComboBox<Account> cbListAccount;

    @FXML
    private Label lbBalance;

    @FXML
    private AnchorPane panelView;

    @FXML
    void onBtnAddAccountClick(MouseEvent event) {
        AddAccountPopup addAccountPopup = (AddAccountPopup)GuiUtils.openPopup(this,"AddAccountPopup");
        addAccountPopup.setParams("accountController",this.accountController);
    }

    @FXML
    void onBtnDailyClick(MouseEvent event) {
        AddDailyPopup addDailyPopup = (AddDailyPopup) setPane(panelView,"AddDailyPopup");
        addDailyPopup.setParams("accountController",this.accountController);
    }

    @FXML
    void onBtnExitClick(MouseEvent event) {
        closeScene(event);
    }

    @FXML
    void onBtnStatsClick(MouseEvent event) {
        AddStatisticsPopup addStatisticsPopup = (AddStatisticsPopup) setPane(panelView,"AddStatisticsPopup");
        addStatisticsPopup.setParams("accountController",this.accountController);
    }

    @FXML
    void onSelectAccount(ActionEvent event) {
        if(cbListAccount.getValue() != null) {
            Account account = cbListAccount.getValue();

            lbBalance.setText(account.getBalance().toString());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.accountController = new AccountController();
        this.initListAccount();
    }

    public void initListAccount(){
        ObservableList<Account> list = FXCollections.observableList(this.accountController.getListAccount());
        cbListAccount.getItems().clear();
        cbListAccount.setItems(list);
    }

    public void onAddNewAccount(Account account){
        this.accountController.insert(account);
        this.initListAccount();
        int index = this.cbListAccount.getItems().indexOf(account);
        this.cbListAccount.getSelectionModel().select(index);
    }

}
