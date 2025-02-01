package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.MessageMailDAO;
import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MessageMailManagement {

    @Inject
    private MessageMailDAO messageMailDAO;

    @Inject
    private PersonDAO personDAO;

    public void addMessageMail(MessageMail messageMail) throws Exception {
        if(messageMail == null) throw new Exception("MENSAJE DE MAIL NO VALIDO");
        Person person = personDAO.getPersonsByEmail(messageMail.getMailDestination());
        if(person == null) throw new Exception("NO EXISTE UN USUARION DESTINO CON ESE MAIL");
        messageMail.setPersonId(person.getId());
        this.messageMailDAO.insertMessageMail(messageMail);
    }

    public void updateMessageMailStatus(int idMessageMail) throws Exception {
        if(idMessageMail <= 0) throw new Exception("");
        MessageMail messageMail = this.messageMailDAO.readMessageMail(idMessageMail);
        if(messageMail == null) throw new Exception("");
        messageMail.setStatus("RD");
        this.messageMailDAO.updateMessageMail(messageMail);
    }

    public List<MessageMail> getMessageMailsPerPerson(int id_person) throws Exception {
        if(id_person < 0) throw new Exception("CODIGO DE LA PERSONA NO VALIDO");
        Person person = this.personDAO.readPerson(id_person);
        if(person == null) throw new Exception("NO SE ENCONTRO UNA PERSONA CON ESTE ID");
        return  this.messageMailDAO.getMessageMailsPerPerson(id_person);
    }

}
