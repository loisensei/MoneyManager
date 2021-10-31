package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.utils.TimeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class DatePickerPane extends BaseView{
    private final ObservableList<String> listKindOfTime = FXCollections.observableArrayList();
    private AppConst.RANGE_TIME rangeTime;
    private LocalDate currentTime;
    private Runnable callback;

    @FXML
    private HBox btnAddOfDaily;

    @FXML
    private JFXComboBox<String> cbKindOfTime;

    @FXML
    private HBox hbTime;

    @FXML
    private Label lbDateView;

    @FXML
    void onActionKindOfTime(ActionEvent event) {
        String s = cbKindOfTime.getValue();
        switch (s) {
            case "All" -> {
                this.setRangeTime(AppConst.RANGE_TIME.ALL);
                hbTime.setVisible(false);
            }
            case "Day" -> {
                this.setRangeTime(AppConst.RANGE_TIME.DAY);
                hbTime.setVisible(true);
            }
            case "Month" -> {
                this.setRangeTime(AppConst.RANGE_TIME.MONTH);
                hbTime.setVisible(true);
            }
            case "Year" -> {
                this.setRangeTime(AppConst.RANGE_TIME.YEAR);
                hbTime.setVisible(true);
            }
        }
    }

    @FXML
    void onClickBackward(MouseEvent event) {
        if (this.currentTime == null) return;
        this.currentTime = TimeUtils.previousTimeWithRange(this.currentTime,this.rangeTime);
        this.reloadTxtTime();
        this.onChangeTime();
    }

    @FXML
    void onClickForward(MouseEvent event) {
        if (this.currentTime == null) return;
        this.currentTime = TimeUtils.nextTimeWithRange(this.currentTime,this.rangeTime);
        this.reloadTxtTime();
        this.onChangeTime();
    }

    public void setRangeTime(AppConst.RANGE_TIME rangeTime){
        this.rangeTime = rangeTime;
        if (this.currentTime == null){
            this.currentTime = LocalDate.now();
        }
        this.reloadTxtTime();
        this.onChangeTime();
    }

    private void reloadTxtTime(){
        String s = switch (this.rangeTime) {
            case DAY -> currentTime.toString();
            case MONTH -> String.valueOf(currentTime.getMonth()) + String.valueOf(currentTime.getYear());
            case YEAR -> String.valueOf(currentTime.getYear());
            case ALL -> "";
        };
        this.lbDateView.setText(s);
    }

    public void setChangeTimeListener(Runnable callback){
        this.callback = callback;
    }

    private void onChangeTime(){
        if (this.callback != null){
            this.callback.run();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listKindOfTime.addAll("All","Day","Month","Year");
        cbKindOfTime.setItems(listKindOfTime);
        // default
        this.setRangeTime(AppConst.RANGE_TIME.ALL);
        cbKindOfTime.getSelectionModel().select("All");
        hbTime.setVisible(false);
    }

    public LocalDate getCurrentTime(){
        return this.currentTime;
    }
    public AppConst.RANGE_TIME getRangeTime(){
        return this.rangeTime;
    }
}
