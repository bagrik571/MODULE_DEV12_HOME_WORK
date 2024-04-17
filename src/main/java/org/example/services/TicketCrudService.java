package org.example.services;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;


public class TicketCrudService {

//    //create new ticket
//    public boolean createTicket(Ticket ticket){
//        boolean result = false;
//        try(Session session = HibernateUtils.getInstance().getSessionFactory().openSession()){
//            Transaction transaction = session.beginTransaction();
//            try {
//                session.merge(ticket);
//                transaction.commit();
//                result = true;
//            } catch (Exception ex){
//                ex.printStackTrace();
//                transaction.rollback();
//            }
//        }
//        return result;
//    }
//create new ticket with checking
public boolean createTicket(Ticket ticket){
    boolean result = false;
    try(Session session = HibernateUtils.getInstance().getSessionFactory().openSession()){
        Transaction transaction = session.beginTransaction();
        try {
            // Проверяем существование клиента
            Client client = session.get(Client.class, ticket.getClient().getId());
            if (client == null) {
                // Клиент не существует, билет не может быть создан
                throw new IllegalArgumentException("Client does not exist");
            }

            // Проверяем существование планеты отправления
            Planet fromPlanet = session.get(Planet.class, ticket.getFromPlanet().getId());
            if (fromPlanet == null) {
                // Планета отправления не существует, билет не может быть создан
                throw new IllegalArgumentException("From Planet does not exist");
            }

            // Проверяем существование планеты прибытия
            Planet toPlanet = session.get(Planet.class, ticket.getToPlanet().getId());
            if (toPlanet == null) {
                // Планета прибытия не существует, билет не может быть создан
                throw new IllegalArgumentException("To Planet does not exist");
            }

            session.merge(ticket);
            transaction.commit();
            result = true;
        } catch (Exception ex){
            ex.printStackTrace();
            transaction.rollback();
        }
    }
    return result;
}


    //update ticket info
    public boolean updateTicket(Ticket ticket){
        boolean result = false;
        if (Objects.isNull(ticket.getId())) {
            return false;
        }
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(ticket);
                transaction.commit();
                result = true;
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return result;
    }
//get ticket by ticket id
    public Ticket getTicketById (Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            //return session.get(Ticket.class, ticketId);
            return session.createQuery(
                            "SELECT t FROM Ticket t JOIN FETCH t.client LEFT JOIN FETCH t.fromPlanet LEFT JOIN FETCH t.toPlanet WHERE t.id = :id",
                            Ticket.class
                    )
                    .setParameter("id", ticketId)
                    .getSingleResult();
        }
    }

//get ticket by client id
    public List<Ticket> getByClientId(Long clientId){
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Ticket c WHERE c.client.id = :id", Ticket.class)
                    .setParameter("id", clientId)
                    .list();
        }
    }
//get all tickets
    public List<Ticket> getAllTickets () {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    //delete ticket by ticket id
    public void deleteTicketById (Long ticketId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket existing = session.get(Ticket.class, ticketId);
            session.remove(existing);
            transaction.commit();
        }
    }
//delete all tickets
    public void deleteTicket (Ticket ticket) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
        }
    }

}
