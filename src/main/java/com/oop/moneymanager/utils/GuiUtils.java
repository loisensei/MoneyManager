package com.oop.moneymanager.utils;

import com.oop.moneymanager.view.BaseView;
import com.oop.moneymanager.view.HomeScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
}
