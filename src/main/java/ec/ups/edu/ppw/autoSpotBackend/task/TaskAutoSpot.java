package ec.ups.edu.ppw.autoSpotBackend.task;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqDealBaseDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.management.ContractManagement;
import ec.ups.edu.ppw.autoSpotBackend.api.management.ScheduleManagement;
import ec.ups.edu.ppw.autoSpotBackend.api.management.TicketManagement;
import ec.ups.edu.ppw.autoSpotBackend.business.ServiceSendMail;
import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import ec.ups.edu.ppw.autoSpotBackend.model.Ticket;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.MessageMail;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Singleton
public class TaskAutoSpot {

    @Inject
    private ContractManagement contractManagement;

    @Inject
    private ScheduleManagement scheduleManagement;

    @Inject
    private ServiceSendMail serviceSendMail;

    @Inject
    private TicketManagement ticketManagement;

    private final Set<Integer> emailsSent = new HashSet<>();



    @Schedule(hour = "0", minute = "0", persistent = false)
    public void contractCheckToday(){
        Date today = new Date();

        List<Contract> contractsExpireToday  = contractManagement.getAllContracts()
                .stream()
                .filter(contract -> "AC".equalsIgnoreCase(contract.getStatus())
                        && isSameDay(today, contract.getEndDate()))
                .toList();
        for (Contract contract : contractsExpireToday){
            if(contract.isAutoRenewal()){
                ReqDealBaseDTO reqDealBaseDTO  = new ReqDealBaseDTO();
                reqDealBaseDTO.getAutomobile().setIdAutomobile(contract.getAutomobile().getIdAutomobile());
                reqDealBaseDTO.getPerson().setIdPerson(contract.getPerson().getIdPerson());
                reqDealBaseDTO.getParkingSpace().setIdParkingSpace(contract.getParkingSpace().getIdParkingSpace());
                reqDealBaseDTO.setIdRate(contract.getRate().getIdRate());
                reqDealBaseDTO.setAutoRenewal(true);
                System.out.println(contract);
                contractManagement.createContract(reqDealBaseDTO);
            }else {
                contractManagement.endContract(contract.getIdDeal());
            }
        }
    }

    @Schedule(second = "0", minute = "*", hour = "*", persistent = false)
    public void emailForDepartureTime(){
        List<ec.ups.edu.ppw.autoSpotBackend.model.Schedule> schedules = scheduleManagement.getAllSchedules();
        Date today = new Date();
        List<Ticket> tickets = ticketManagement.getAllTickets()
                .stream().
                filter( ticket -> "AC".equalsIgnoreCase(ticket.getStatus()))
                .toList();
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
            System.out.println(today);
            long minutesBeforeClosing = getMinutesBetween(todayInSchedule.getClosingTime());
            System.out.println(minutesBeforeClosing);
            System.out.println("Faltan 10 minutos para el horario de cierre: " + todayInSchedule.getClosingTime());
            if (minutesBeforeClosing >= 7 && minutesBeforeClosing <= 10) {
                System.out.println("Faltan 10 minutos para el horario de cierre: " + todayInSchedule.getClosingTime());
                for (Ticket ticket: tickets){
                    final String emailBody = MessageMail.generateContractEndingEmailBody(
                            ticket.getPerson().getName() + " " + ticket.getPerson().getLastName(),
                            ticket.getAutomobile().getLicensePlate(),
                            getMonthDayHourMinute(ticket.getEndDate()),
                            ticket.getParkingSpace().getLocation()
                    );
                    final String emailSubject = "📅 Aviso: de Cierre de el Parkeadero";
                    final String toMail = ticket.getPerson().getMailUser().getMail();
                    this.serviceSendMail.sendEmail(
                            toMail,
                            emailSubject,
                            emailBody
                    );
                }
            }
        }


    }

    private String getMonthDayHourMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return String.format("%02d-%02d %02d:%02d", month, day, hour, minute);
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
        System.out.println(diffInMillis);
        System.out.println(adjustedToday);

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


}