package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Account;

import java.util.List;

public interface IDAO<T> {
    public List<T> getAll();
    public void save(T t);
    public void update(T t);
    public void delete(T t);
}
