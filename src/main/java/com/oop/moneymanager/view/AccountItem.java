package com.oop.moneymanager.view;

import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountItem extends BaseView{
    private Account account = new Account();
    private AccountController accountController = new AccountController();
    @FXML
    private Label lbAccount;

    @FXML
    private Label lbBalance;

    @FXML
    void onBtnDelete(MouseEvent event) {
        this.accountController.delete(this.account);
        AddAccountPopup addAccountPopup = (AddAccountPopup) this.getParam("parent");
        addAccountPopup.reloadListView();
        HomeScene homeScene = (HomeScene) this.getParam("homescene");
        homeScene.actionDelAccount(this.account);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void init(){
        lbAccount.setText(account.getName());
        lbBalance.setText(this.accountController.calCurrentBalance(this.account).toString());
    }
    public void setAccount(Account account){
        this.account = account;
    }
}
