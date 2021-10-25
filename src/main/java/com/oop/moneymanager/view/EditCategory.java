package com.oop.moneymanager.view;

import com.jfoenix.controls.JFXListView;
import com.oop.moneymanager.controller.CategoryController;
import com.oop.moneymanager.model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EditCategory extends BaseView{
    private InputTransactionPopup inputTransactionPopup;
    private CategoryController categoryController;
    private Integer mode;
    @FXML
    private JFXListView<Pane> lvCategories;

    @FXML
    private TextField txtAddCategory;

    @FXML
    void onBtnAddClick(MouseEvent event) {
        this.inputTransactionPopup = (InputTransactionPopup) this.getParam("parent");
        Category category = new Category();
        category.setType(this.mode);
        category.setName(txtAddCategory.getText());
        this.categoryController.add(category);
        loadCategory(this.mode);
        this.inputTransactionPopup.reloadCategories(this.mode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvCategories.setStyle("-fx-selection-bar: white");
        this.categoryController = new CategoryController();
    }
    public void loadCategory(Integer mode){
        this.mode = mode;
        this.lvCategories.getItems().clear();
        List<Category> list = this.categoryController.getByType(mode);
        for(Category category : list) {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource("ItemCategory.fxml"));
            try {
                this.lvCategories.getItems().add(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ItemCategory ctl = (ItemCategory) fxmlLoader.getController();
            ctl.setCategory(category);
        }
    }
}