package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonManagement {
    @Inject
    PersonDAO personDAO;

    public void addPerson(Person person) throws CustomException {
        if(person == null) throw  new CustomException(Errors.BAD_REQUEST,"Person cannot be null");
        if(person.getIdPerson() > 0) throw  new CustomException(Errors.BAD_REQUEST, "Person already have an id");
        personDAO.insertPerson(person);
    }

    public List<UserDTO> getAllPersons(){
        return personDAO.getPersons().stream().map(
                        (person) -> UserDTO.fromPersonModel(person))
                .collect(Collectors.toUnmodifiableList());
    }

    public UserDTO getPersonByDocumentID(String documentID) {
        if (documentID == null || documentID.isEmpty()) throw new CustomException(Errors.BAD_REQUEST, "The document ID is required");
        Person person = personDAO.getPersonByDocumentID(documentID);
        if (person == null) throw new CustomException(Errors.NOT_FOUND, "Person with this document ID not exist");
        return UserDTO.fromPersonModel(person);
    }

    public Person getPerson(String  documentID, int idPerson) throws CustomException {
        Person person = new Person();
        try {
            if(idPerson >  0 ){
                int personExist = this.getPersonById(idPerson).getIdPerson();
                person.setIdPerson(personExist);
            }else if (documentID != null && !documentID.isEmpty()) {
                int personExist = this.getPersonByDocumentID(documentID).getIdPerson();
                person.setIdPerson(personExist);
            }else {
                throw new CustomException(Errors.BAD_REQUEST, "ID persons or documentID is required");
            }
        }catch (Exception e){
            throw new CustomException(Errors.INTERNAL_SERVER_ERROR, "Not found User with this Parameters");
        }
        return person;
    }

    public UserDTO getPersonById(int id_person) throws CustomException {
        if (id_person <= 0) throw new CustomException(Errors.BAD_REQUEST, "Data inconsistency in this Person");
        Person person = personDAO.readPerson(id_person);
        if (person == null) throw new CustomException(Errors.NOT_FOUND, "Person with id not exist");
        UserDTO userDTO = UserDTO.fromPersonModel(person);
        return userDTO;
    }

    public void updatePerson(UserDTO user) throws CustomException {
        if(user == null) throw  new CustomException(Errors.BAD_REQUEST, "User cannot be null");
        Person personFind = this.getPerson(user.getIdPerson());
        this.updateCampssPerson(personFind ,user);
        if(user.getName() == null || user.getName().length() < 3)
            throw new CustomException(Errors.BAD_REQUEST, "The name is requested and it need minimum 4 characters");
        if (user.getPhone() != null && !user.getPhone().isEmpty() && (user.getPhone().length() < 7 || user.getPhone().length() > 15)) {
            throw new CustomException(Errors.BAD_REQUEST, "The phone number must be between 7 and 15 digits");
        }
        if (user.getDocumentID() != null && !user.getDocumentID().isEmpty() && (user.getDocumentID().length() < 10 || user.getDocumentID().length() > 13)) {
            throw new CustomException(Errors.BAD_REQUEST, "The document ID must be between 10 and 13 digits");
        }
        Person personUpdate = this.personDAO.modifyPerson(personFind);
        if(personUpdate == null) throw new CustomException(Errors.INTERNAL_SERVER_ERROR,"Internal error");
    }

    public Person getPersonByMail(String mail) throws  CustomException{
        if(mail.isEmpty()) throw new CustomException(Errors.BAD_REQUEST, "Invalid format mail");
        Person person = this.personDAO.getPersonsByEmail(mail);
        return person;
    }

    public String changeState(int idPerson) throws  CustomException{
        Person person = this.getPerson(idPerson);
        String newStatus = person.getStatus().equals("A")  ? "I" : "A";
        person.setStatus(newStatus);
        this.personDAO.modifyPerson(person);
        return  newStatus.equals("A")  ? "Active" : "Inactive";
    }

    public boolean personExistByMail(String mail){
        return this.personDAO.getPersonsByEmail(mail) != null;
    }


    private Person updateCampssPerson(Person person, UserDTO userDTO){
        person.setName(userDTO.getName());
        person.setLastName(userDTO.getLastName());
        person.setDocumentID(userDTO.getDocumentID());
        person.setPhone(userDTO.getPhone());
        person.setLocation(userDTO.getLocation());
        person.setBirthDay(userDTO.getBirthDay());
        person.setMailS(userDTO.getMailS());
        return person;
    }

    private Person getPerson(int id_person ){
        if (id_person <= 0) throw new CustomException(Errors.BAD_REQUEST, "Data inconsistency in this Person");
        Person person = personDAO.readPerson(id_person);
        if (person == null) throw new CustomException(Errors.NOT_FOUND, "Person with id not exist");
        return person;

    }

}