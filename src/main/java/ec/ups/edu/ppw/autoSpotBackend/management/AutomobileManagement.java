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
        if(automobile== null) throw  new Exception("DATOS DE EL AUTOMOVILE REGISTRADO");
        automobileDAO.insertAutomobile(automobile);
    }

    public List<Automobile> getAllAutomobiles() throws Exception {
        return automobileDAO.getAutomobiles();
    }

    public Automobile getAutomobileById(int id_autmobile) throws  Exception {
        if (id_autmobile <= 0) throw new Exception("AUTMOBILE CON EL ID: " + id_autmobile + " INVALIDA...");
        Automobile automobile = automobileDAO.readAutomobile(id_autmobile);
        if (automobile == null) throw new Exception("AUTMOBILE CON EL ID: " + id_autmobile + " NO ENCONTRADA.");
        return automobile;
    }

    public void updateAutomobile(Automobile automobile) throws Exception{
        if(automobile == null) throw  new Exception("NO EXISTE DATOS EN ESTE AUTMOIBILE");
        if(automobile.getId() <= 0) throw  new Exception("AUTOMOBILE SIN ID PARA ACTUALIZAR");
        if(this.getAutomobileById(automobile.getId()) == null) throw new Exception("AUTOMOBILE CON EL ID: " + automobile.getId() + " NO ECONTRADO.");
        Automobile automobileUpdate = this.automobileDAO.updateAutomobile(automobile);
        if( automobileUpdate == null) throw new Exception("NO SE PUEDE MODIFICAR A EL AUTOMOBILE");
    }
}
