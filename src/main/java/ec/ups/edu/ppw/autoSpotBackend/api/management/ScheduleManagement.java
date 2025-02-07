package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.model.Schedule;
import ec.ups.edu.ppw.autoSpotBackend.dao.ScheduleDAO;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationScoped

public class ScheduleManagement {
    @Inject
    private ScheduleDAO scheduleDAO;

    public void addSchedule(Schedule shedule) throws CustomException {
        if(shedule == null) throw  new CustomException(Errors.BAD_REQUEST, "Schedule is null");
        if(shedule.getStatus().toUpperCase().compareTo("R") == 0) throw  new CustomException(Errors.BAD_REQUEST, "Schedule is empty");
        if(shedule.getStatus().toUpperCase().compareTo("E") != 0 && shedule.getStatus().toUpperCase().compareTo("NW") != 0)  throw  new CustomException(Errors.BAD_REQUEST, "Schedule is empty");
        this.validatorDate(shedule);
        shedule.setStatus(shedule.getStatus().toUpperCase());
        scheduleDAO.insertSchedule(shedule);
    }

    public Schedule readSchedule(int id_schedule) throws CustomException {
        return this.validatorForSchedule(id_schedule);
    }
    public void removeSchedule(int id_schedule) throws CustomException {
        Schedule schedule = this.validatorForSchedule(id_schedule);
        if(schedule.getStatus().toUpperCase().compareTo("R") == 0) throw  new CustomException(Errors.BAD_REQUEST, "Schedule is empty");
        scheduleDAO.deleteSchedule(id_schedule);
    }

    public void updateSchedule(Schedule shedule) throws CustomException {
        validatorForSchedule(shedule.getIdDay());
        validatorDate(shedule);
        shedule.setStatus(shedule.getStatus().toUpperCase());
        this.scheduleDAO.modifySchedule(shedule);

    }

    public List<Schedule> getAllSchedules() throws CustomException {
        return this.scheduleDAO.getSchedules();
    }

    private Schedule validatorForSchedule(int id_schedule) throws CustomException {
        if ( id_schedule <= 0) throw new CustomException(Errors.BAD_REQUEST, "Schedule is empty");
        Schedule shedule = scheduleDAO.readSchedule(id_schedule);
        if(shedule == null) throw new CustomException(Errors.NOT_FOUND, "Schedule not found");
        return shedule;
    }

    private void validatorDate(Schedule schedule) throws CustomException {
        Date openingTime = schedule.getOpeningTime();
        Date closingTime = schedule.getClosingTime();
        if(openingTime == null || closingTime == null) throw  new CustomException(Errors.BAD_REQUEST, "Schedules has not to be null or empty");

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        Date currentDate = currentCalendar.getTime();

//        if(currentDate.before(openingTime)) throw  new Exception("NO SE PUEDE MODIFICAR FECHAS PASADAS");
        if(closingTime.before(openingTime)) throw  new CustomException(Errors.UNAUTHORIZED, "Schedules has to be after opening time");
    }


}
