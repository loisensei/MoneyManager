package com.oop.moneymanager.view;

import com.oop.moneymanager.controller.AccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAccountPopup extends BaseView{

    @FXML
    private Button btnAddAccount;

    @FXML
    private Label lbMesseger;

    @FXML
    private TextField txtAccountName;

    @FXML
    private TextField txtBalance;

    @FXML
    void onBtnAddAccountClick(ActionEvent event) {
        HomeScene hs = (HomeScene) this.getParam("parent");
        hs.test();
        AccountController ac = (AccountController) this.getParam("accountController");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
