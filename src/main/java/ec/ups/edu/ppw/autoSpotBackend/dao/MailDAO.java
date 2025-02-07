package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Mail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class MailDAO {
    private EntityManager em;

    public void insertMail(Mail mail) {
        em.persist(mail);
    }

    public Mail getMailById(int id) {
        return em.find(Mail.class, id);
    }

    public Mail getMailByEmail(String mail) {
        String jpql = "SELECT m FROM Mail m WHERE m.mail = :mail";
        Query q = em.createQuery(jpql, Mail.class);
        q.setParameter("mail", mail);
        List<Mail> mails = q.getResultList();
        return (mails.isEmpty() ? null : mails.get(0));
    }

    public List<Mail> getAllMails() {
        return em.createQuery("select m from Mail m", Mail.class).getResultList();
    }



}
