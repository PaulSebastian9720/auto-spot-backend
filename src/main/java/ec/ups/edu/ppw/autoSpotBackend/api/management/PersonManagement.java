package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PersonManagement {
    @Inject
    PersonDAO personDAO;

    public void addPerson(Person person) throws CustomException {
        if(person == null) throw  new CustomException(Errors.BAD_REQUEST,"Person cannot be null");
        if(person.getIdPerson() > 0) throw  new CustomException(Errors.BAD_REQUEST, "Person already have an id");
        personDAO.insertPerson(person);
    }

    public List<Person> getAllPersons(){
        return personDAO.getPersons();
    }

    public Person getPersonById(int id_person) throws CustomException {
        if (id_person <= 0) throw new CustomException(Errors.BAD_REQUEST, "Data inconsistency in this Person");
        Person person = personDAO.readPerson(id_person);
        if (person == null) throw new CustomException(Errors.NOT_FOUND, "Person with id not exist");
        return person;
    }

    public void updatePerson(Person person) throws CustomException {
        if(person == null) throw  new CustomException(Errors.BAD_REQUEST, "Person cannot be null");
        if(person.getIdPerson() <= 0) new CustomException(Errors.BAD_REQUEST,"Person with id is out of range");
        if(this.getPersonById(person.getIdPerson()) == null) throw new CustomException(Errors.NOT_FOUND, "Person with id is not found");
        Person personUpdate = this.personDAO.modifyPerson(person);
        if(personUpdate == null) throw new CustomException(Errors.INTERNAL_SERVER_ERROR,"Internal error");
    }

    public String changeState(int idPerson) throws  CustomException{
        Person person = this.getPersonById(idPerson);
        String newStatus = person.getStatus().equals("A")  ? "I" : "A";
        person.setStatus(newStatus);
        this.personDAO.modifyPerson(person);
        return  newStatus.equals("A")  ? "Active" : "Inactive";
    }
}
