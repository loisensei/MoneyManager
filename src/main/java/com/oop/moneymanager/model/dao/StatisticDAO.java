package com.oop.moneymanager.model.dao;

import com.oop.moneymanager.model.Statistic;
import com.oop.moneymanager.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StatisticDAO implements IStatisticDAO{
    private SessionFactory sessionFactory;
    public StatisticDAO(){
        this.sessionFactory = HibernateUtils.getConnection();
    }
    @Override
    public List<Statistic> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Statistic> statistics = session.createQuery("FROM Statistic ",Statistic.class).list();
        session.close();
        return statistics;
    }

    @Override
    public void save(Statistic statistic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(statistic);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Statistic statistic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(statistic);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Statistic statistic) {

    }

    @Override
    public void delete(Statistic statistic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(statistic);
        session.getTransaction().commit();
        session.close();
    }
}
