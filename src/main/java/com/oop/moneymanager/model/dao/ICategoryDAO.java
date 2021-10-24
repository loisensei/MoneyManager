package com.oop.moneymanager.model.dao;


import com.oop.moneymanager.model.Category;

import java.util.List;

public interface ICategoryDAO extends IDAO<Category>{
    public boolean isEmpty();
    public List<Category> getByType(Integer type);
}
