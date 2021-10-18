package com.oop.moneymanager.view;

import javafx.fxml.Initializable;

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
}
