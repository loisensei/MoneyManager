package com.oop.moneymanager.view;

import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.utils.GuiUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemTransaction extends BaseView{
    private Transaction transaction;

    @FXML
    private Label lbAmount;

    @FXML
    private Label lbCategory;

    @FXML
    private Label lbTime;

    @FXML
    private Label lbNote;

    @FXML
    void onEditTransactionClick(MouseEvent event) {
        InputTransactionPopup inputTransactionPopup = (InputTransactionPopup) GuiUtils.openPopup(this,"InputTransactionPopup");
        Scene scene = (Scene) inputTransactionPopup.getParam("scene");
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Chỉnh sửa");
        inputTransactionPopup.setData(this.transaction);
    }
    
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        lbAmount.setText(transaction.getAmount().toString());
        if(transaction.getType().equals(AppConst.CATEGORY_TYPE.INCOME)){
            lbAmount.setStyle("-fx-text-fill: blue");
        }else{
            lbAmount.setStyle("-fx-text-fill: red");
        }
        lbCategory.setText(transaction.getCategory().toString());
        lbTime.setText(transaction.getTime().toString());
        if(transaction.getNote() != null) {
            lbNote.setText(transaction.getNote());
        }
    }
    public Transaction getTransaction() {
        return this.transaction;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
