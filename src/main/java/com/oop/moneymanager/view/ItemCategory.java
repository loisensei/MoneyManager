package com.oop.moneymanager.view;

import com.oop.moneymanager.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCategory extends BaseView{

    @FXML
    private ImageView onBtnEditCategoryClick;

    @FXML
    private Label lbCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setCategory(Category category){
        lbCategory.setText(category.getName());
    }
}
