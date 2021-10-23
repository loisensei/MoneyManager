package com.oop.moneymanager.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public abstract class BaseView implements Initializable {
    private HashMap<String,Object> params;

    public BaseView(){
        this.params = new HashMap<>();
    }

    public void setParams(String key, Object value) {
        this.params.put(key,value);
    }
    public Object getParam(String key){
        return this.params.get(key);
    }
    public void closeScene(Event action){
        Node source = (Node)  action.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public Object setPane(Pane paneName, String sourceName){
        paneName.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScene.class.getResource(sourceName+".fxml"));
        try {
            paneName.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader.getController();
    }
}
