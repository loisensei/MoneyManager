package com.oop.moneymanager.view;

import com.oop.moneymanager.controller.CategoryController;
import com.oop.moneymanager.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCategory extends BaseView{
    private CategoryController categoryController;
    private Category category;
    private Integer mode;
    @FXML
    private ImageView onBtnEditCategoryClick;

    @FXML
    private Label lbCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.categoryController = new CategoryController();
    }
    public void setCategory(Category category){
        this.category = category;
        lbCategory.setText(category.getName());
    }
    public void setMode(Integer mode){
        this.mode = mode;
    }
    @FXML
    void onBtnDeleteCategoryClick(MouseEvent event) {
        this.categoryController.remove(this.category);
        EditCategory editCategory = (EditCategory) this.getParam("parent");
        editCategory.loadCategories(this.mode);
    }
}
