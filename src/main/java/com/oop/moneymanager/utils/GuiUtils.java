package com.oop.moneymanager.utils;

import com.oop.moneymanager.view.BaseView;
import com.oop.moneymanager.view.HomeScene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class GuiUtils {
    public static Object openPopup(Object parent, String popupName) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource(popupName+".fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        BaseView popupController = fxmlLoader.getController();
        popupController.setParams("parent",parent);
        return popupController;
    }
    public static Object setPane(Pane pane, String sourceName){
        pane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource(sourceName+".fxml"));
        try {
            pane.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseView popupController = fxmlLoader.getController();
        popupController.setParams("parent",pane.getParent());
        return popupController;
    }
    public static void addNumericInputListener(TextField tf){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
