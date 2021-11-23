package com.oop.moneymanager.view;

import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.utils.GuiUtils;
import com.oop.moneymanager.utils.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddAccountPopup extends BaseView{
    private HomeScene hs;
    private AccountController accountController = new AccountController();
    @FXML
    private AnchorPane acpAddAccount;

    @FXML
    private VBox hbDelAccount;

    @FXML
    private ListView<Pane> lvAccount;

    @FXML
    private Button btnAddAccount;

    @FXML
    private Button btnDeleteAccount;

    private Integer mode;

    @FXML
    private Label lbMesseger;

    @FXML
    private TextField txtAccountName;

    @FXML
    private TextField txtBalance;

    @FXML
    private HBox pBackground;

    @FXML
    void onBtnAddAccountClick(ActionEvent event) {
        this.hs = (HomeScene) this.getParam("parent");
        AccountController ac = (AccountController) this.getParam("accountController");
        if(ac.isExist(txtAccountName.getText())){
            lbMesseger.setText("Account already exists!");
        }else{
            if(isValidInput()) {
                Account account = new Account(txtAccountName.getText().trim(),Integer.parseInt(txtBalance.getText()));
                hs.onAddNewAccount(account);
                closeScene(event);
            }else{
                lbMesseger.setText("Error!");
            }
        }

    }
    @FXML
    void onBtnModeAdd(ActionEvent event) {
        setMode(AppConst.ACCOUNT_MODE.ADD);

    }

    @FXML
    void onBtnModeDelete(ActionEvent event) {
        setMode(AppConst.ACCOUNT_MODE.DEL);
        reloadListView();
    }

    Boolean isValidInput(){
        return StringUtils.isValidAccountName(txtAccountName.getText()) && StringUtils.isValidBalance(txtBalance.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GuiUtils.addNumericInputListener(this.txtBalance);
        System.out.println("alo");
        setMode(AppConst.ACCOUNT_MODE.ADD);
    }

    private void setMode(Integer mode){
        if(Objects.equals(mode,AppConst.ACCOUNT_MODE.ADD)){
            pBackground.setStyle("-fx-background-color:  #2A2D36");
            btnAddAccount.setStyle("-fx-text-fill: white");
            btnAddAccount.setStyle("-fx-background-color: #FD5200");
            btnDeleteAccount.setStyle("-fx-text-fill: white");
            btnDeleteAccount.setStyle("-fx-background-color:  #F4DABD");
            this.acpAddAccount.setVisible(true);
            this.hbDelAccount.setVisible(false);
        }else {
            pBackground.setStyle("-fx-background-color:  white");
            btnDeleteAccount.setStyle("-fx-text-fill: white");
            btnDeleteAccount.setStyle("-fx-background-color: #FD5200");
            btnAddAccount.setStyle("-fx-text-fill: white");
            btnAddAccount.setStyle("-fx-background-color:  #F4DABD");
            this.acpAddAccount.setVisible(false);
            this.hbDelAccount.setVisible(true);
        }
    }

    public void reloadListView() {
        List<Account> accounts= this.accountController.getListAccount();
        this.lvAccount.getItems().clear();
        for(Account account : accounts){
            FXMLLoader loader = new FXMLLoader(HomeScene.class.getResource("AccountItem.fxml"));
            try {
                this.lvAccount.getItems().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            AccountItem accountItem = loader.getController();
            accountItem.setAccount(account);
            accountItem.init();
            accountItem.setParams("parent",this);
            accountItem.setParams("homescene",this.getParam("parent"));
        }
    }
}
