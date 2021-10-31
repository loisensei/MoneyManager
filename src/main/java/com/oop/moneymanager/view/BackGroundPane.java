package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXComboBox;
import com.oop.moneymanager.AppConst;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class BackGroundPane {
    private AppConst.RANGE_TIME rangeTime;
    private LocalDate currentTime;

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

    }

    @FXML
    void onAddTransactionClick(MouseEvent event) {

    }

    @FXML
    void onClickBackward(MouseEvent event) {

    }

    @FXML
    void onClickForward(MouseEvent event) {

    }
}
