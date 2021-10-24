package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.controller.AccountController;
import com.oop.moneymanager.utils.GuiUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class DailyPane extends BaseView{
    private AccountController accountController;
    private HomeScene homeScene;
    public DailyPane(){
        this.homeScene = (HomeScene) this.getParam("parent");
        this.accountController = (AccountController) this.getParam("accountController");
    }
    private final ObservableList<String> listKindOfTime = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<String> cbKindOfTime;

    @FXML
    private Label lbDateView;

    @FXML
    private ListView<Pane> listViewStatistic;

    @FXML
    void onActionKindOfTime(ActionEvent event) {

    }

    @FXML
    void onClickAddStatistic(MouseEvent event) {
        InputTransactionPopup addSpendingPopup = (InputTransactionPopup) GuiUtils.openPopup(this,"AddSpendingPopup");
        addSpendingPopup.setParams("accountController",this.accountController);
    }

    @FXML
    void onClickForward(MouseEvent event) {

    }

    @FXML
    void onClickBackward(MouseEvent event) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listKindOfTime.addAll("Day","Month","Year");
        cbKindOfTime.setItems(listKindOfTime);

    }
}
