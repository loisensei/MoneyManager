package com.oop.moneymanager.view;

import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.utils.GuiUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        AccountController ac = (AccountController) this.getParam("accountController");
        if(ac.isExist(txtAccountName.getText())){
            lbMesseger.setText("Account already exists!");
        }else{
            Account account = new Account(txtAccountName.getText(),Integer.parseInt(txtBalance.getText()));
            if(isValidInput()) {
                ac.insert(account);
                hs.initListAccount();
                closeScene(event);
            }else{
                lbMesseger.setText("Error!");
            }
        }

    }

    Boolean isValidInput(){
        if(txtAccountName.getText() == null){
            return false;
        }
        return txtBalance.getText() != null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GuiUtils.addNumericInputListener(this.txtBalance);
    }
}
