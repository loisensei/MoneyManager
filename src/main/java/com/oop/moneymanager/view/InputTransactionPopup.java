package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.controller.CategoryController;
import com.oop.moneymanager.controller.TransactionController;
import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.utils.GuiUtils;
import com.oop.moneymanager.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class InputTransactionPopup extends BaseView {
    private CategoryController categoryController;
    private Transaction transaction;
    private Integer mode;
    @FXML
    private Label lbWarningAmount;

    @FXML
    private Label lbWarningCategory;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
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
    void onBtnEditCategoryClick(MouseEvent event) {
        EditCategory editCategory = (EditCategory) GuiUtils.openPopup(this,"EditCategory");
        editCategory.loadCategories(this.mode);
        Scene popupEditCategories = (Scene) editCategory.getParam("scene");
        Stage stage = (Stage) popupEditCategories.getWindow();
        if(Objects.equals(this.mode, AppConst.CATEGORY_TYPE.INCOME)){
            stage.setTitle("Thu");
        }else{
            stage.setTitle("Chi");
        }
        editCategory.setLabelCategory(this.mode);
        popupEditCategories.getWindow().setOnCloseRequest(e->this.reloadCategories(this.mode));
    }

    @FXML
    void onBtnAddClick(ActionEvent event) {
        Category category = cbCategories.getValue();

        boolean isValidInput = true;
        if (category == null) {
            lbWarningCategory.setVisible(true);
            isValidInput = false;
        }
        if (!StringUtils.isValidBalance(txtAmount.getText())) {
            lbWarningAmount.setVisible(true);
            isValidInput = false;
        }
        if (!isValidInput) return;
        Integer amount = Integer.valueOf(txtAmount.getText());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setTime(Date.valueOf(dpTime.getValue()));
        transaction.setNote(txtNote.getText());
        if(btnAdd.getText().equals("Thêm")) {
            DailyPane dailyPane = (DailyPane) this.getParam("parent");
            dailyPane.onAddTransaction(transaction);
        }
        //Change Transaction :
        if(btnAdd.getText().equals("Sửa")) {
            transaction.setId(this.transaction.getId());
            ItemTransaction itemTransaction = (ItemTransaction) this.getParam("parent");
            itemTransaction.onUpdateTransaction(transaction);
        }
        closeScene(event);

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
        GuiUtils.addNumericInputListener(txtAmount);
        LocalDate localDate = LocalDate.now();
        dpTime.setValue(localDate);


    }
    public void setMode(Integer mode){
        this.mode = mode;
        List<Category> categories;
        categories = categoryController.getByType(mode);
        resetCategories(categories);
        setModeColor(mode);
    }

    public void setModeColor(Integer mode){
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

    public void reloadCategories(Integer mode){
        List<Category> list = categoryController.getByType(mode);
        resetCategories(list);
    }

    public void setData(Transaction transaction){
        Integer mode = transaction.getType();
        Category category = transaction.getCategory();
        reloadCategories(mode);
        setModeColor(mode);
        btnAdd.setText("Sửa");
        dpTime.setValue(transaction.getTime().toLocalDate());
        cbCategories.getSelectionModel().select(category);
        txtAmount.setText(transaction.getAmount().toString());
        txtNote.setText(transaction.getNote());
        this.transaction = transaction;
    }
    @FXML
    void onBtnDeleteTransactionClick(ActionEvent event) {
        ItemTransaction itemTransaction = (ItemTransaction) this.getParam("parent");
        itemTransaction.onDeleteTransaction();
        closeScene(event);
    }
    public void setBtnDeleteTransaction(){
        this.btnDelete.setVisible(true);
    }
}
