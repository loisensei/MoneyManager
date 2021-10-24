package com.oop.moneymanager.controller;

import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.model.dao.ITransactionDAO;
import com.oop.moneymanager.model.dao.MysqlImp.TransactionDAO;

import java.util.List;

public class TransactionController {
    private ITransactionDAO transactionDAO;
    private AccountController accountController;
    private Account account;
    public TransactionController(Account account){
        this.accountController = new AccountController();
        this.transactionDAO = new TransactionDAO();
        this.setAccount(account);

    }

    public List<Transaction> getAll(){
        return transactionDAO.getByAccountId(this.account.getId());
    }

    public void setAccount(Account account){
        this.account = account;
    }
    public void add(Transaction transaction){
        transaction.setAccount(this.account);
        transactionDAO.save(transaction);
    }

}
