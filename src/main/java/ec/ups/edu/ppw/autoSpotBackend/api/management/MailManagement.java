package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.MailDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Mail;
import jakarta.inject.Inject;

import java.util.List;

public class MailManagement {

    @Inject
    MailDAO mailDAO;

    public void insertMail(Mail mail) {
        mailDAO.insertMail(mail);
    }

    public Mail getMail(int id) {
        return mailDAO.getMailById(id);
    }

    public Mail getMailbyMail(String mail) {
        return mailDAO.getMailByEmail(mail);
    }

    public List<Mail> getMails() {
        return mailDAO.getAllMails();
    }
}
