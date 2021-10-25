package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXListView;
import com.oop.moneymanager.controller.CategoryController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCategory extends BaseView{
    private InputTransactionPopup tp;
    private CategoryController categoryController;
    @FXML
    private JFXListView<Pane> lvCategories;

    @FXML
    void onBtnAddClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tp = (InputTransactionPopup) this.getParam("parent");
        categoryController = (CategoryController) this.getParam("categoryController");
    }


}