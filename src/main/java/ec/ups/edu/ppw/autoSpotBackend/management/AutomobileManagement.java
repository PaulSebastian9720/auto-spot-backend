package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.AutomobileDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AutomobileManagement {
    @Inject
    private AutomobileDAO automobileDAO;

    public void addAutomobile(Automobile automobile) throws Exception {
        if(automobile== null) throw  new Exception("DATOS DE EL AUTOMOVILE SON NECESARIOS");
        if(automobile.getPersonId() <= 0)throw new Exception("ERROR EN EL CODIGO DE LA PERSONA");
        if(automobile.getLicensePlate().compareTo("") == 0) throw new Exception("LA PLACA ES NESCESARIA");
        boolean isPlateExist  = this.automobileDAO.getAutomobileByLicensePlate(automobile.getLicensePlate()) != null;
        if(isPlateExist) throw new Exception("UN AUTOMOBILE CON ES PLACA YA HA SIDO REGISTRADA");
        automobileDAO.insertAutomobile(automobile);
    }

    public List<Automobile> getAllAutomobiles() throws Exception {
        return automobileDAO.getAutomobiles();
    }


    public Automobile getAutomobileById(int id_autmobile) throws  Exception {
        if (id_autmobile <= 0) throw new Exception("AUTMOBILE CON EL ID: " + id_autmobile + "ID DE EL AUTOMOVILE INVALIDO");
        Automobile automobile = automobileDAO.readAutomobile(id_autmobile);
        if (automobile == null) throw new Exception("AUTMOBILE CON EL ID: " + id_autmobile + "NO SE ENCONTRO AUTOMOBILES CON ESE ID");
        return automobile;
    }

    public void updateAutomobile(Automobile automobile) throws Exception{
        if(automobile == null) throw  new Exception("NO EXISTE DATOS EN ESTE AUTMOIBILE");
        if(automobile.getIdAutomobile() <= 0) throw  new Exception("AUTOMOBILE SIN ID PARA ACTUALIZAR");
        if(automobile.getLicensePlate().compareTo("") == 0) throw new Exception("LA PLACA ES NESCESARIA");
        if(this.getAutomobileById(automobile.getIdAutomobile()) == null) throw new Exception("AUTOMOBILE CON EL ID: " + automobile.getIdAutomobile() + " NO ECONTRADO.");
        if(automobile.getPersonId() <= 0) throw new Exception("AUTOMOBILE SIN ID DE LA PERSONA PARA ACTUALIZAR");
        Automobile  automobilePlateExist = this.automobileDAO.getAutomobileByLicensePlate(automobile.getLicensePlate());
        if(automobilePlateExist != null && automobilePlateExist.getIdAutomobile() != automobile.getIdAutomobile() ) throw new Exception("UN AUTOMOBILE CON ES PLACA YA HA SIDO REGISTRADA");
        Automobile automobileUpdate = this.automobileDAO.updateAutomobile(automobile);
        if( automobileUpdate == null) throw new Exception("NO SE PUEDO ACTUALIZAR EL AUTOMOBILE");
    }

    public List<Automobile> getListByIDPerson(int id_person) throws Exception{
        if(id_person <= 0) throw  new Exception("Datos no existen");
        List<Automobile> listAutomobile = this.automobileDAO.getListByIDPerson(id_person);
        return listAutomobile;
    }

    public void removeAutomobile(int id_autmobile) throws Exception {
        this.getAutomobileById(id_autmobile);
        boolean exist = this.automobileDAO.existContractsWhitThisAutomobile(id_autmobile);
        if(exist) throw new Exception("EXISTE CONTRATOS ASOCIADOS A ESTE AUTOMOBILE Y NO SE PUEDE ELIMINAR");
        automobileDAO.deleteAutomobile(id_autmobile);
    }
}
