package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.controller.CategoryController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddSpendingPopup extends BaseView {
    private CategoryController categoryController;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnExpense;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private JFXComboBox<Category> cbCategories;

    @FXML
    private DatePicker dpTime;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextArea txtNote;

    @FXML
    void onBtnAddClick(ActionEvent event) {

    }

    @FXML
    void onBtnExpenseClick(ActionEvent event) {
        this.setMode(AppConst.CATEGORY_TYPE.EXPENSE);
    }

    @FXML
    void onBtnIncomeClick(ActionEvent event) {
        this.setMode(AppConst.CATEGORY_TYPE.INCOME);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryController = new CategoryController();
        setMode(AppConst.CATEGORY_TYPE.EXPENSE);

    }
    public void setMode(Integer mode){
        List<Category> categories;
        categories = categoryController.getByType(mode);
        resetCategories(categories);

        if (Objects.equals(mode, AppConst.CATEGORY_TYPE.EXPENSE)) {
            btnAdd.setStyle("-fx-background-color: #FD5200");
            btnExpense.setStyle("-fx-background-color: #FD5200");
            btnIncome.setStyle("-fx-background-color: #F4F4F4");

        } else {
            btnAdd.setStyle("-fx-background-color: #02ADFF");
            btnIncome.setStyle("-fx-background-color: #02ADFF");
            btnExpense.setStyle("-fx-background-color: #F4F4F4");
        }
    }

    private void resetCategories(List<Category> categories){
        ObservableList<Category> list = FXCollections.observableList(categories);
        cbCategories.getItems().clear();
        cbCategories.setItems(list);
    }
}
