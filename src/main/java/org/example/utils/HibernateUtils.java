package org.example.utils;

import lombok.Getter;
import org.example.prop.PropertyReader;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final HibernateUtils INSTANCE = new HibernateUtils();
    @Getter
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private HibernateUtils() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
        flywayMigration(PropertyReader.getConnectionUrlForPostgres(),
                PropertyReader.getUserForPostgres(),
                PropertyReader.getPasswordForPostgres());
    }

    public static HibernateUtils getInstance() {
        return INSTANCE;
    }



    public void closeSessionFactory() {
        this.sessionFactory.close();
    }

    /* Flyway */

    private void flywayMigration(String connectionUrl, String username, String password) {
        Flyway flyway = Flyway.configure().dataSource(connectionUrl, username, password).locations("classpath:/migration").load();
        flyway.migrate();
    }
}
