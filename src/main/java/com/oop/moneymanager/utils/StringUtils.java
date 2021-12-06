package com.oop.moneymanager.utils;

public class StringUtils {
    public static Boolean isValidAccountName(String name){
        return name.trim().length() > 0;
    }

    public static Boolean isValidBalance(String balance){
        try{
            Integer n = Integer.parseInt(balance);
            return true;
        } catch (Exception ignored){
            return false;
        }
    }
    public static Boolean isValidBalanceAddAccount(String balance){
        if(balance.equals("")) return true;
        try{
            Integer n = Integer.parseInt(balance);
            return true;
        } catch (Exception ignored){
            return false;
        }
    }
}
