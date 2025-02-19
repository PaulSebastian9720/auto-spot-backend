package ec.ups.edu.ppw.autoSpotBackend.task;


import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.management.PersonManagement;
import ec.ups.edu.ppw.autoSpotBackend.api.management.ScheduleManagement;
import ec.ups.edu.ppw.autoSpotBackend.business.ServiceSendMail;
import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.model.Ticket;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.MessageMail;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class TaskOpenParking {
    @Inject
    private ScheduleManagement scheduleManagement;

    @Inject
    private ServiceSendMail serviceSendMail;

    @Inject
    private PersonDAO personDAO;

    @Schedule(second = "0", minute = "*", hour = "*", persistent = false)
    public void SendMessageOpenParking(){
        List<ec.ups.edu.ppw.autoSpotBackend.model.Schedule> schedules = scheduleManagement.getAllSchedules();
        Date today = new Date();
        ec.ups.edu.ppw.autoSpotBackend.model.Schedule todayInSchedule = null;
        String todayDayName = getDayNameInEnglish(today);

        for (ec.ups.edu.ppw.autoSpotBackend.model.Schedule schedule : schedules) {
            if ("E".equalsIgnoreCase(schedule.getStatus()) && isSameDay(schedule.getExceptionDay(), today)) {
                todayInSchedule = schedule;
            }
        }
        if(todayInSchedule == null){
            for (ec.ups.edu.ppw.autoSpotBackend.model.Schedule schedule : schedules) {
                if (todayDayName.equalsIgnoreCase(schedule.getDayName()) && "R".equalsIgnoreCase(schedule.getStatus())) {
                    todayInSchedule = schedule;
                }
            }
        }

        if (todayInSchedule != null) {
            long minutesBeforeClosing = getMinutesBetween(todayInSchedule.getOpeningTime());
            if (minutesBeforeClosing == 5) {
                List<Person> listPersons = this.personDAO.getPersons();
                for (Person person: listPersons){
                    final String emailBody = MessageMail.generateParkingWelcomeEmail(
                            person.getName() + " " + person.getLastName()
                    );
                    final String emailSubject = "¡AutoSpot ya está abierto y listo para ti! 🎉";
                    final String toMail = person.getMailUser().getMail();
                    this.serviceSendMail.sendEmail(
                            toMail,
                            emailSubject,
                            emailBody
                    );
                }
            }
        }
    }

    private long getMinutesBetween(Date toDate) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar toDateCalendar = Calendar.getInstance();
        toDateCalendar.setTime(toDate);
        nowCalendar.set(Calendar.HOUR_OF_DAY, toDateCalendar.get(Calendar.HOUR_OF_DAY));
        nowCalendar.set(Calendar.MINUTE, toDateCalendar.get(Calendar.MINUTE));
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        Date adjustedToday = nowCalendar.getTime();
        long diffInMillis = adjustedToday.getTime() - new Date().getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    private String getDayNameInEnglish(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case Calendar.SUNDAY: return "domingo";
            case Calendar.MONDAY: return "lunes";
            case Calendar.TUESDAY: return "martes";
            case Calendar.WEDNESDAY: return "miércoles";
            case Calendar.THURSDAY: return "jueves";
            case Calendar.FRIDAY: return "viernes";
            case Calendar.SATURDAY: return "sábado";
            default: return "Unknown";
        }
    }
}
