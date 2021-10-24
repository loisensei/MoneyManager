package com.oop.moneymanager.model.dao.MysqlImp;

import com.oop.moneymanager.model.Transaction;
import com.oop.moneymanager.model.dao.ITransactionDAO;
import com.oop.moneymanager.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TransactionDAO implements ITransactionDAO {
    private SessionFactory sessionFactory;
    public TransactionDAO(){
        this.sessionFactory = HibernateUtils.getConnection();
    }
    @Override
    public List<Transaction> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Transaction> statistics = session.createQuery("FROM Transaction ", Transaction.class).list();
        session.close();
        return statistics;
    }

    @Override
    public void save(Transaction statistic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(statistic);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Transaction statistic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(statistic);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Transaction statistic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(statistic);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public List<Transaction> getByAccountId(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Transaction> list = session.createQuery("from Transaction where Account_id = "+id, Transaction.class).list();
        session.close();
        return list;
    }
}
