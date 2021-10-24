package com.oop.moneymanager.view;

import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemTransaction{
    private Transaction transaction;

    @FXML
    private Label lbAmount;

    @FXML
    private Label lbCategory;

    @FXML
    private Label lbTime;

    @FXML
    private Label lbNote;
    
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

}
