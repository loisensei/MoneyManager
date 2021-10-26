package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXListView;
import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.controller.CategoryController;
import com.oop.moneymanager.model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EditCategory extends BaseView{
    private InputTransactionPopup inputTransactionPopup;
    private CategoryController categoryController;
    private Integer mode;
    @FXML
    private ListView<Pane> lvCategories;

    @FXML
    private Label lbCategory;

    @FXML
    private TextField txtAddCategory;

    @FXML
    void onBtnAddClick(MouseEvent event) {
        this.inputTransactionPopup = (InputTransactionPopup) this.getParam("parent");
        String categoryName = txtAddCategory.getText().trim().toLowerCase();
        categoryName = String.valueOf(categoryName.charAt(0)).toUpperCase() + categoryName.substring(1);
        Category category = new Category();
        category.setType(this.mode);
        category.setName(categoryName);
        this.categoryController.add(category);
        loadCategories(this.mode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvCategories.setStyle("-fx-selection-bar: white");
        this.categoryController = new CategoryController();
    }

    public void loadCategories(Integer mode){
        this.mode = mode;
        List<Category> list = this.categoryController.getByType(mode);

        this.lvCategories.getItems().clear();
        for(Category category : list) {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource("ItemCategory.fxml"));
            try {
                this.lvCategories.getItems().add(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ItemCategory itemCategory = (ItemCategory) fxmlLoader.getController();
            itemCategory.setCategory(category);
            itemCategory.setMode(this.mode);
            itemCategory.setParams("parent",this);
        }
    }

    private void closeProgram(){
        this.inputTransactionPopup.reloadCategories(this.mode);
    }
    public void setLabelCategory(Integer mode){
        if(mode.equals(AppConst.CATEGORY_TYPE.INCOME)){
            lbCategory.setStyle("-fx-text-fill: Blue");
        }else{
            lbCategory.setStyle("-fx-text-fill: #FD5200");
        }
    }

}