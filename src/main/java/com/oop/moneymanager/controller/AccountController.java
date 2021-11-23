package com.oop.moneymanager.controller;

import com.oop.moneymanager.AppConst;
import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.model.dao.ITransactionDAO;
import com.oop.moneymanager.model.dao.MysqlImp.AccountDAO;
import com.oop.moneymanager.model.dao.IAccountDAO;
import com.oop.moneymanager.model.dao.MysqlImp.TransactionDAO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class AccountController {
    private ITransactionDAO transactionDAO;
    private IAccountDAO accountDAO;
    public AccountController(){
        this.transactionDAO = new TransactionDAO();
        this.accountDAO = new AccountDAO();
    }



    public List<Account> getListAccount(){
        return this.accountDAO.getAll();
    }

    public boolean isExist(String name){
        return accountDAO.isExist(name);
    }

    public void insert(Account account){
        accountDAO.save(account);
    }
    public Integer calCurrentBalance(Account account){
        List<Transaction> list = transactionDAO.getByAccountId(account.getId());
        Integer balance = account.getInitialBalance();
        for(Transaction transaction : list){
            int amount = transaction.getAmount();
            Integer type = transaction.getCategory().getType();
            if(type.equals(AppConst.CATEGORY_TYPE.EXPENSE)) amount = -amount;
            balance += amount;
        }
        return balance;
    }
    public void delete(Account account){
        this.accountDAO.delete(account);

    }
}
