package com.oop.moneymanager.controller;

import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.dao.ICategoryDAO;
import com.oop.moneymanager.model.dao.MysqlImp.CategoryDAO;

import java.util.List;

public class CategoryController {
    private ICategoryDAO categoryDAO;
    public CategoryController(){
        this.categoryDAO = new CategoryDAO();
    }
    public List<Category> getByType(Integer type){
        return categoryDAO.getByType(type);
    }

}
