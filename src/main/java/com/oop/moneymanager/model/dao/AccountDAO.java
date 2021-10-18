package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AccountDAO implements IAccountDAO{
    private SessionFactory sessionFactory;
    public AccountDAO(){
        this.sessionFactory = HibernateUtils.getConnection();
    }

    @Override
    public Boolean isExist(String accountName) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Account> list = session.createQuery("FROM Account",Account.class).list();
        session.close();
        return list;
    }

    @Override
    public void save(Account account) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void add(Account account) {

    }

    @Override
    public void delete(Account account) {

    }
}
