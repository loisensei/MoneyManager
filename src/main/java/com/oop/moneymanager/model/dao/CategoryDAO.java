package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Category;
import com.oop.moneymanager.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
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
    public void add(Category category) {

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
        return session.createQuery("FROM Category ",Category.class).list().isEmpty();
    }
}
