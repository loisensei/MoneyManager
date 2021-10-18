package com.oop.moneymanager.controller;

import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.dao.AccountDAO;

import java.util.List;

public class AccountController {
    private AccountDAO accountDAO;
    public AccountController(){
        this.accountDAO = new AccountDAO();
    }

    public List<Account> getListAccount(){
        return this.accountDAO.getAll();
    }
}
