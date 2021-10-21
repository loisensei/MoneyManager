package com.oop.moneymanager.utils;

public class StringUtils {
    public static Boolean isValidAccountName(String name){
        return name.trim().length() > 0;
    }

    public static Boolean isValidBalance(String balance){
        // todo: full check
        return balance.length() > 0;
    }
}
