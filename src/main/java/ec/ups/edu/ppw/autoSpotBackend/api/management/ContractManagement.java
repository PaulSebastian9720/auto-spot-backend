package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqContractDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.ContractDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.*;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ContractManagement {
    @Inject
    private ContractDAO contractDAO;
    @Inject
    private SpaceManagement spaceManagement;
    @Inject
    private PersonManagement personManagement;
    @Inject
    private AutomobileManagement automobileManagement;
    @Inject
    private RateManagement rateManagement;

    public Contract getContract(int idContract) throws CustomException {
        if(idContract <= 0) throw new CustomException(Errors.BAD_REQUEST,"The idContact is out of range");
        Contract contract = this.contractDAO.readContract(idContract);
        if(contract == null) throw new CustomException(Errors.NOT_FOUND ,"Not found contract with this id");
        return contract;
    }

    @Transactional
    public void createContract(ReqContractDTO reqContractDTO) throws CustomException {
        Person person = this.personManagement.getPerson(
                reqContractDTO.getPerson().getDocumentID()
                ,reqContractDTO.getPerson().getIdPerson()
        );
        Automobile automobile = this.automobileManagement.getAndCreateAutomobile(
                reqContractDTO.getAutomobile().getLicensePlate(),
                reqContractDTO.getAutomobile().getIdAutomobile(),
                person.getIdPerson()
        );
        ParkingSpace parkingSpace = this.spaceManagement.getParkingSpace(
                reqContractDTO.getParkingSpace().getLocation(),
                reqContractDTO.getParkingSpace().getIdParkingSpace()
        );

        if(!parkingSpace.getStatus().equalsIgnoreCase("FR"))
            throw new CustomException(Errors.BAD_REQUEST,"The parking space with this location is not available");
        Rate rate = this.rateManagement.getRateById(reqContractDTO.getIdRate());
        if(!rate.getTimeUnit().equalsIgnoreCase("1_month"))
            throw new CustomException(Errors.BAD_REQUEST, "The rate is not available in Contracts");

        Contract contract = new Contract();
        contract.setRate(rate);
        contract.setPerson(person);
        contract.setParkingSpace(parkingSpace);
        contract.setStartDate(new Date());
        contract.setAutomobile(automobile);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();
        contract.setEndDate(endDate);
        contract.setStatus("AC");
        contract.setFinalPrice(rate.getPrize());
        this.contractDAO.insertContract(contract);
        Contract contractSearch = this.getContractByLocation(parkingSpace.getLocation());
        parkingSpace.setDealBase(contractSearch);
        parkingSpace.setStatus("BC");
        this.spaceManagement.updateParkingSpace(parkingSpace);
    }


    public Contract getContractByLocation(String location) throws  CustomException{
        if(location == null || location.isEmpty()) throw  new CustomException(Errors.BAD_REQUEST, "The location cannot null");
        Contract contract = this.contractDAO.getContractByLocation(location);
        if(contract == null) throw new CustomException(Errors.NOT_FOUND, "The contract with this location does not exist");
        return contract;
    }

    public List<Contract> getAllContracts() throws  Exception {
        List<Contract> contracts = this.contractDAO.getContracts();
        return contracts;
    }

    public List<Contract> getContractsByIdPerson(int idPerson) throws Exception {
        if(idPerson <= 0) throw new Exception("ID DE PERSONA INVALIDO");
        List<Contract> contracts = this.contractDAO.getContractsByIdPerson(idPerson);
        return contracts;

    }
}