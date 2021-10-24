package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Transaction;

import java.util.List;

public interface ITransactionDAO extends IDAO<Transaction>{
    public List<Transaction> getByAccountId(Integer id);
}
