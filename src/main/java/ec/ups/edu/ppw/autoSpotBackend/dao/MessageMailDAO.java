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

    public void insertMessageMail(MessageMail messageMail) {em.persist(messageMail);}
    public MessageMail updateMessageMail(MessageMail messageMail) {
        return em.merge(messageMail);
    }

    public MessageMail readMessageMail(int id) {
        return em.find(MessageMail.class, id);
    }

    public List<MessageMail> getMessageForQuery(String query) {
        Query q = em.createNativeQuery(query);
        return q.getResultList();
    }
}
