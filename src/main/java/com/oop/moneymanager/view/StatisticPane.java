package com.oop.moneymanager.view;


import com.jfoenix.controls.JFXButton;
import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.controller.CategoryController;
import com.oop.moneymanager.controller.TransactionController;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StatisticPane extends BasePane{
    private TransactionController transactionController;
    private CategoryController categoryController;
    private DatePickerPane datePickerPane;
    private Integer mode;
    @FXML
    private JFXButton btnExpense;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private PieChart pcStatistic;

    @FXML
    private Pane pnDatePicker;

    @FXML
    void onBtnExpenseClick(ActionEvent event) {
        setMode(AppConst.CATEGORY_TYPE.EXPENSE);
    }

    @FXML
    void onBtnIncomeClick(ActionEvent event) {
        setMode(AppConst.CATEGORY_TYPE.INCOME);
    }
    @Override
    public void reload(Account account) {
        if(this.transactionController==null) {
            this.transactionController = new TransactionController(account);
        }else {
            transactionController.setAccount(account);
        }

        loadData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDatePickerPane();
        initButton();
        this.mode = AppConst.CATEGORY_TYPE.EXPENSE;
    }

    public void setMode(Integer mode){
        this.mode = mode;
        setModeColor(mode);
        resetText();
        loadPieChart();
    }
    public void resetText(){
        Integer amount;
        amount = this.transactionController.sumTransaction(AppConst.CATEGORY_TYPE.INCOME,datePickerPane.getRangeTime(),datePickerPane.getCurrentTime());
        btnIncome.setText("Thu: "+amount);
        amount = this.transactionController.sumTransaction(AppConst.CATEGORY_TYPE.EXPENSE,datePickerPane.getRangeTime(),datePickerPane.getCurrentTime());
        btnExpense.setText("Chi: "+amount);
    }
    public void loadPieChart(){
        List<PieChart.Data> listData = this.transactionController.ListPieChartData(this.mode,datePickerPane.getRangeTime(),datePickerPane.getCurrentTime());
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(listData);
        pcStatistic.setData(list);
    }
    public void loadData() {
        resetText();
        loadPieChart();
        setMode(this.mode);
    }
    public void setModeColor(Integer mode){
        if(Objects.equals(mode, AppConst.CATEGORY_TYPE.INCOME)){
            btnIncome.setStyle("-fx-background-color: #02ADFF");
            btnExpense.setStyle("-fx-background-color: #C3C3C2");
        }else{
            btnExpense.setStyle("-fx-background-color: #FD5200");
            btnIncome.setStyle("-fx-background-color: #C3C3C2");
        }
    }

    private void initButton(){
        setModeColor(AppConst.CATEGORY_TYPE.EXPENSE);
    }

    private void initDatePickerPane(){
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource("DatePickerPane.fxml"));
        try {
            pnDatePicker.getChildren().add(fxmlLoader.load());
            this.datePickerPane = fxmlLoader.getController();
            this.datePickerPane.setChangeTimeListener(this::loadData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
