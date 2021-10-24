package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.dao.MysqlImp.TransactionDAO;
import com.oop.moneymanager.utils.GuiUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScene extends BaseView {
    private AccountController accountController;
    private BasePane currentPane;

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
        if(cbListAccount.getValue() != null) {
            this.setPane("DailyPane");
        }
    }
    @FXML
    void onBtnExitClick(MouseEvent event) {
        closeScene(event);
    }

    @FXML
    void onBtnStatsClick(MouseEvent event) {
        this.setPane("AddStatisticsPopup");
    }

    @FXML
    void onSelectAccount(ActionEvent event) {
        updateBalance();
        if (this.currentPane != null){
            this.currentPane.reload(cbListAccount.getValue());
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

    public static void main(String[] args) {
        TransactionDAO transactionDAO = new TransactionDAO();
        System.out.println(transactionDAO.getByAccountId(1));
//        AppConst.WeekDay w = AppConst.WeekDay;
    }

    public void updateBalance(){
        if(cbListAccount.getValue() != null) {
            Account account = cbListAccount.getValue();
            Integer balance = accountController.calCurrentBalance(account);
            lbBalance.setText(balance.toString());
        }
    }

    private void setPane(String sourceName){
        panelView.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource(sourceName+".fxml"));
        try {
            panelView.getChildren().add(fxmlLoader.load());
            this.currentPane = fxmlLoader.getController();
            this.currentPane.setHomeScene(this);
            this.currentPane.reload(cbListAccount.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
