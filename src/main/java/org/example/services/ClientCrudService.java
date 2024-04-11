package org.example.services;

import org.example.utils.HibernateUtils;
import org.example.entity.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

public class ClientCrudService {
    public boolean createClient(Client client){
        boolean result = false;
        try(Session session = HibernateUtils.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            try {
                client.setId(null);
                session.persist(client);
                transaction.commit();
                result = true;
            } catch (Exception ex){
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return result;
    }

    public boolean updateClient(Client client){
        boolean result = false;
        if (Objects.isNull(client.getId())) {
            return false;
        }
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(client);
                transaction.commit();
                result = true;
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return result;
    }

    public Client getClientById (Long clientId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Client.class, clientId);
        }
    }

    public List<Client> getAllClients () {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }
    public void deleteClientById (Long clientId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client existing = session.get(Client.class, clientId);
            session.remove(existing);
            transaction.commit();
        }
    }

    public void deleteAllClients () {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Client> query = session.createQuery("DELETE FROM Client", Client.class);
            int rowsAffected = query.executeUpdate();
            transaction.commit();
            System.out.println("Deleted " + rowsAffected + " clients");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
