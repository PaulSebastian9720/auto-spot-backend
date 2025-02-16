package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.AutomobileDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AutomobileManagement {
    @Inject
    private AutomobileDAO automobileDAO;
    @Inject
    private PersonManagement personManagement;

    public void addAutomobile(Automobile automobile) throws CustomException {
        this.validators(automobile);
        automobileDAO.insertAutomobile(automobile);
    }

    public Automobile getAutomobileById(int id_automobile)  throws CustomException {
        if (id_automobile <= 0) throw new CustomException(Errors.BAD_REQUEST,"Invalid id_automobile");
        Automobile automobile = automobileDAO.readAutomobile(id_automobile);
        if (automobile == null) throw new CustomException(Errors.NOT_FOUND,"Automobile with id is not found");
        return automobile;
    }

    public void updateAutomobile(Automobile automobile) throws CustomException{
        if(this.getAutomobileById(automobile.getIdAutomobile()) == null) throw new CustomException(Errors.NOT_FOUND, "Automobile with id not found");
        this.validators(automobile);
        Automobile automobileUpdate = this.automobileDAO.updateAutomobile(automobile);
        if( automobileUpdate == null) throw new CustomException(Errors.INTERNAL_SERVER_ERROR,"Cannot update this automobile");
    }

    public Automobile getByLicencePlate(String licensePlate) throws  CustomException{
        if(licensePlate == null || licensePlate.isEmpty()) throw new CustomException(Errors.BAD_REQUEST, "The license plate is required");
        Automobile automobile = this.automobileDAO.getAutomobileByLicensePlate(licensePlate);
        return automobile;

    }

    public List<Automobile> getListByIDPerson(int id_person) throws CustomException {
        this.personManagement.getPersonById(id_person);
        return this.automobileDAO.getListByIDPerson(id_person);
    }

    public void removeAutomobile(int id_automobile) throws CustomException {
        this.getAutomobileById(id_automobile);
        if(this.automobileDAO.existContractsWhitThisAutomobile(id_automobile))
            throw new CustomException(Errors.INTERNAL_SERVER_ERROR,"There are contracts o tickets related to this automobile");
        automobileDAO.deleteAutomobile(id_automobile);
    }

    public Automobile getAndCreateAutomobile(String licensePlate, int idAutomobile, int personID ){
        Automobile automobile = new Automobile();
        int idExist;
        if(idAutomobile > 0){
            try {
                 idExist = this.getAutomobileById(idAutomobile).getIdAutomobile();
            } catch (Exception e){
                throw new CustomException(Errors.NOT_FOUND, "Not found Automobile with this ID");
            }

            boolean personIsOwner = false;
            for (Automobile auto : this.getListByIDPerson(personID)) {
                if(auto.getIdAutomobile() == idExist){
                    personIsOwner = true;
                    automobile = auto;
                    break;
                }
            }
            if(!personIsOwner)
                throw new CustomException(Errors.BAD_REQUEST, "The person is not owner of this automobile");

        } else if (licensePlate != null && !licensePlate.isEmpty()){
            Automobile autoSearch = null;
            try {
                autoSearch = this.getByLicencePlate(licensePlate);
            }catch (Exception e){}

            if(autoSearch != null) {
                boolean personIsOwner = false;
                for (Automobile auto : this.getListByIDPerson(personID)) {
                    if (autoSearch.getIdAutomobile() == auto.getIdAutomobile()) {
                        personIsOwner = true;
                        automobile = autoSearch;
                        break;
                    }
                }
                if (!personIsOwner)
                    throw new CustomException(Errors.BAD_REQUEST, "The person is not owner of this automobile");

            }else {
                Person person = new Person();
                person.setIdPerson(personID);
                Automobile newAutomobile = new Automobile();
                newAutomobile.setPerson(person);
                newAutomobile.setLicensePlate(licensePlate);
                this.addAutomobile(newAutomobile);
                automobile = this.getByLicencePlate(licensePlate);
            }
        }else {
            throw new CustomException(Errors.BAD_REQUEST, "ID automobile or license plate is required");
        }

        return automobile;
    }

    private void  validators( Automobile automobile) throws CustomException {
        if(automobile== null) throw  new CustomException(Errors.BAD_REQUEST, "Automobile cannot null");
        if(automobile.getIdPerson() <= 0)throw  new CustomException(Errors.BAD_REQUEST, "Automobile required a Person");
        this.personManagement.getPersonById(automobile.getPerson().getIdPerson());
        if(automobile.getLicensePlate().compareTo("") == 0) throw  new CustomException(Errors.BAD_REQUEST, "The license plate is necessary");
        Automobile  automobilePlateExist = this.automobileDAO.getAutomobileByLicensePlate(automobile.getLicensePlate());
        if(automobilePlateExist != null && automobilePlateExist.getIdAutomobile() != automobile.getIdAutomobile() )
            throw new CustomException(Errors.BAD_REQUEST,"There is already a Automobile with this plate");
    }
}
