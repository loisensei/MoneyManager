package com.oop.moneymanager.controller;

import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.model.dao.ITransactionDAO;
import com.oop.moneymanager.model.dao.MysqlImp.TransactionDAO;

public class TransactionController {
    private ITransactionDAO transactionDAO;
    private AccountController accountController;
    private Account account;
    public TransactionController(Account account){
        this.accountController = new AccountController();
        this.transactionDAO = new TransactionDAO();
        this.setAccountId(account);

    }

    public void setAccountId(Account account){
        this.account = account;
    }
    public void add(Transaction statistic){
        statistic.setAccount(this.account);
        transactionDAO.save(statistic);
    }

}
