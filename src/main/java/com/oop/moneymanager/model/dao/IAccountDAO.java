package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Account;

import java.util.List;

public interface IAccountDAO extends IDAO<Account> {
    public Boolean isExist(String accountName);
}
