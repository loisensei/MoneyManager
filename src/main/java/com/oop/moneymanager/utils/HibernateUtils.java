package com.oop.moneymanager.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.TimeZone;

public class HibernateUtils {
    static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder() //
                .configure() // Load hibernate.cfg.xml from resource folder by default
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getConnection(){
        if (sessionFactory == null) {
            HibernateUtils.sessionFactory = HibernateUtils.buildSessionFactory();
        }
        return HibernateUtils.sessionFactory;
    }
}
