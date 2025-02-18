package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Ticket;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class TicketDAO {
    @PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
    private EntityManager em;

    public void insertTicket(Ticket ticket){
        em.persist(ticket);
    }

    public Ticket getTicketById(int id){
        return em.find(Ticket.class, id);
    }

    public void modifyTicket(Ticket ticket){
        em.merge(ticket);
    }

    public Ticket getTicketByLocation(String location){
        String jplql = "SELECT t FROM Ticket t WHERE t.parkingSpace.location = :location AND t.status = 'AC'";
        Query q = em.createQuery(jplql, Ticket.class);
        q.setParameter("location", location);
        List<Ticket>  resultList = q.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Ticket getTicketByAccessTicket(String access){
        String jplql = "SELECT t FROM Ticket t WHERE t.accessTicket = :access";
        Query q = em.createQuery(jplql, Ticket.class);
        q.setParameter("access", access);
        List<Ticket> resultList = q.getResultList();
        return resultList.isEmpty()? null : resultList.get(0);
    }

    public List<Ticket> getTicketsByIdPerson(int idPerson){
        String jpql = "SELECT t FROM  Ticket t WHERE t.person.idPerson = :idPerson";
        Query q = em.createQuery(jpql, Ticket.class);
        q.setParameter("idPerson", idPerson);
        List<Ticket> tickets = q.getResultList();
        return tickets;
    }

    public List<Ticket> getTicketList() {
        String jplql = "SELECT t FROM Ticket t";
        Query q = em.createQuery(jplql, Ticket.class);
        List<Ticket> ticketList = q.getResultList();
        return ticketList;
    }
}
