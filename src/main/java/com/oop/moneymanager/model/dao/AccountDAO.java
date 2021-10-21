package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Account;
import com.oop.moneymanager.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class AccountDAO implements IAccountDAO{
    private SessionFactory sessionFactory;
    public AccountDAO(){
        this.sessionFactory = HibernateUtils.getConnection();
    }

    @Override
    public Boolean isExist(String accountName) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        String hql = "select A.name from Account A where A.name = '"+ accountName+"'";
        Query query = session.createQuery(hql);
        return !query.getResultList().isEmpty();
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
