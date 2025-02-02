package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.MessageMailDAO;
import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MessageMailManagement {

    @Inject
    private MessageMailDAO messageMailDAO;

    @Inject
    private PersonDAO personDAO;

    public void addMessageMail(MessageMail messageMail) throws CustomException {
        if(messageMail == null) throw new CustomException(Errors.BAD_REQUEST, "Message mail is null");
        Person person = personDAO.getPersonsByEmail(messageMail.getMailDestination());
        if(person == null) throw new CustomException(Errors.NOT_FOUND, "Person not found");
        messageMail.setPersonId(person.getIdPerson());
        this.messageMailDAO.insertMessageMail(messageMail);
    }

    public void updateMessageMailStatus(int idMessageMail) throws CustomException {
        if(idMessageMail <= 0) throw new CustomException(Errors.BAD_REQUEST, "Id's message mail is out of range");
        MessageMail messageMail = this.messageMailDAO.readMessageMail(idMessageMail);
        if(messageMail == null) throw new CustomException(Errors.NOT_FOUND, "Message mail cannot be null");
        messageMail.setStatus("RD");
        this.messageMailDAO.updateMessageMail(messageMail);
    }

    public List<MessageMail> getMessageMailsPerPerson(int id_person) throws CustomException {
        if(id_person < 0) throw new CustomException(Errors.BAD_REQUEST, "Id's person is out of range");
        Person person = this.personDAO.readPerson(id_person);
        if(person == null) throw new CustomException(Errors.BAD_REQUEST, "Person is null");
        return  this.messageMailDAO.getMessageMailsPerPerson(id_person);
    }

}
