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