package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PersonManagement {
    @Inject
    PersonDAO personDAO;

    public void addPerson(Person person) throws Exception {
        if(person == null) throw  new Exception("Datos de la persona no validos");
        personDAO.insertPerson(person);
    }

    public List<Person> getAllPersons(){
        return personDAO.getPersons();
    }

    public Person getPersonById(int id_person) throws  Exception {
        if (id_person <= 0) throw new Exception("PERSONA CON EL ID: " + id_person + " INVALIDA...");
        Person person = personDAO.readPerson(id_person);
        if (person == null) throw new Exception("PESONA CON EL ID: " + id_person + " NO ENCONTRADA.");
        return person;
    }

    public void updatePerson(Person person) throws Exception{
        if(person == null) throw  new Exception("NO EXISTE DATOS EN ESTA PERSONA");
        if(person.getId() <= 0) throw  new Exception("PERSONA SIN ID PARA ACTUALIZAR");
        if(this.getPersonById(person.getId()) == null) throw new Exception("PERSONA CON EL ID: " + person.getId() + " NO ECONTRADA.");
        Person personUpdate = this.personDAO.modifyPerson(person);
        if(personUpdate == null) throw new Exception("NO SE PUEDE MODIFICAR A LA PERSONA");
    }
}
