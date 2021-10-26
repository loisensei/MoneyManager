package com.oop.moneymanager.model.dao.MysqlImp;

import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.model.dao.ICategoryDAO;
import com.oop.moneymanager.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    private SessionFactory sessionFactory;
    public CategoryDAO(){
        this.sessionFactory = HibernateUtils.getConnection();
    }
    @Override
    public List<Category> getAll() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Category> categories = session.createQuery("FROM Category ",Category.class).list();
        session.close();
        return categories;
    }

    @Override
    public void save(Category category) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Category category) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Category category) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean isEmpty() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Category> list = session.createQuery("FROM Category ",Category.class).list();
        session.close();
        return list.isEmpty();
    }

    @Override
    public List<Category> getByType(Integer type) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Category> list = session.createQuery("FROM Category where type = "+type.toString(),Category.class).list();
        session.close();
        return list;
    }

    @Override
    public Category getByName(String name) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Category> list = session.createQuery("FROM Category where name = '"+name+"'",Category.class).list();
        if(list.size() == 0){
            return null;
        }
        session.close();
        return list.get(0);
    }

    @Override
    public Integer getId(String name) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Category> list = session.createQuery("FROM Category where name = '"+name+"'",Category.class).list();
        if(list.size() == 0){
            return null;
        }
        session.close();
        return list.get(0).getId();
    }


}
