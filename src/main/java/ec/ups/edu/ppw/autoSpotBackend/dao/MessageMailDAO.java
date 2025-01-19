package ec.ups.edu.ppw.autoSpotBackend.dao;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class MessageMailDAO {
    @PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
    private EntityManager em;


    public void insertMessageMail(MessageMail messageMail) {
        em.persist(messageMail);
    }


    public MessageMail readMessageMail(int id) {
        return em.find(MessageMail.class, id);
    }

    public void updateMessageMail(MessageMail messageMail) {
        em.merge(messageMail);
    }

    public List<MessageMail> getMessageMailsPerPerson(int id_person) {
        String jpql = "SELECT m FROM MessageMail m WHERE m.person.id = :id_person";
        Query query = em.createQuery(jpql, MessageMail.class);
        query.setParameter("id_person", id_person);
        return query.getResultList();
    }

}
