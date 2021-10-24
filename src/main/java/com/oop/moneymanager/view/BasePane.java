package com.oop.moneymanager.view;

import com.oop.moneymanager.model.Account;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BasePane extends BaseView{
    protected HomeScene homeScene;

    public abstract void reload(Account account);

    public void setHomeScene(HomeScene homeScene){
        this.homeScene = homeScene;
    }
}
