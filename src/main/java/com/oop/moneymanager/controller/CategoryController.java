package com.oop.moneymanager.controller;

import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.dao.ICategoryDAO;
import com.oop.moneymanager.model.dao.MysqlImp.CategoryDAO;

import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    private ICategoryDAO categoryDAO;
    public CategoryController(){
        this.categoryDAO = new CategoryDAO();
    }
    public void add(Category category){
        category.setIsVisible(true);
        this.categoryDAO.save(category);
    }
    public List<Category> getByType(Integer type){
        List<Category> list = categoryDAO.getByType(type);
        List<Category> result = new ArrayList<>();
        for(Category c: list){
            if (c.isVisible) result.add(c);
        }
        return result;
    }

}
