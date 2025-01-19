package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.model.Schedule;
import ec.ups.edu.ppw.autoSpotBackend.dao.ScheduleDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped

public class ScheduleManagement {
    @Inject
    private ScheduleDAO scheduleDAO;

    public void addSchedule(Schedule shedule) throws Exception {
        if(shedule == null) throw  new Exception("DATOS NO VALIDOS");
        if(shedule.getStatus().toUpperCase().compareTo("R") == 0) throw  new Exception("NO SE PUEDE REGISTRAR MAS DIAS REGULARES");
        if(shedule.getStatus().toUpperCase().compareTo("E") != 0 && shedule.getStatus().toUpperCase().compareTo("NW") != 0)  throw  new Exception("NO CUMPLE CON LOS REQUISITOS");
        shedule.setStatus(shedule.getStatus().toUpperCase());
        scheduleDAO.insertSchedule(shedule);
    }

    public Schedule readSchedule(int id_schedule) throws Exception {
        return this.validatorForSchedule(id_schedule);
    }
    public void removeSchedule(int id_schedule) throws Exception {
        Schedule schedule = this.validatorForSchedule(id_schedule);
        if(schedule.getStatus().toUpperCase().compareTo("R") == 0) throw  new Exception("NO SE PUEDE ELIMINAR UN DIA REGULAR");
        scheduleDAO.deleteSchedule(id_schedule);
    }

    public void updateSchedule(Schedule shedule) throws Exception {
        validatorForSchedule(shedule.getIdDay());
        shedule.setStatus(shedule.getStatus().toUpperCase());
        this.scheduleDAO.modifySchedule(shedule);

    }

    public List<Schedule> getAllSchedules() throws Exception {
        return this.scheduleDAO.getSchedules();
    }

    private Schedule validatorForSchedule(int id_schedule) throws Exception {
        if ( id_schedule <= 0) throw new Exception("Dia CON EL ID: " + id_schedule + " INVALIDO...");
        Schedule shedule = scheduleDAO.readSchedule(id_schedule);
        if(shedule == null) throw new Exception("NO EXISTE UN DIA CON ESE ID");
        return shedule;
    }

}
