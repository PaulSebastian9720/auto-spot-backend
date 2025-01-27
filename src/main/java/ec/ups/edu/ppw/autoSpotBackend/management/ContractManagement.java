package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.ContractDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ContractManagement {
    @Inject
    private ContractDAO contractDAO;
    @Inject
    private SpaceManagement spaceManagement;

    public Contract getContract(int idContract) throws Exception {
        if(idContract <= 0) throw new Exception("NO EXSITE UN CODIGO CON ESTE CONTRATO");
        Contract contract = this.contractDAO.readContract(idContract);
        if(contract == null) throw new Exception("NO EXISTE UN CONTRATO CON ESTE ID");
        contract.getRates().size();
        return contract;
    }

    public void createContract(Contract contract) throws Exception {
        this.validatorsContract(contract);
        ParkingSpace parkingSpaceContract = contract.getParkingSpace();

        if(contract.getType().toUpperCase().compareTo("MT") == 0 ){
            parkingSpaceContract.setStatus("BT");

            contract.setParkingSpace(parkingSpaceContract);
            Date currentDate = new Date();
            contract.setStartDate(currentDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 1);
            contract.setEndDate(calendar.getTime());
            contract.setStatus("AC");
            parkingSpaceContract.setContract(contract);
            contract.setParkingSpace(parkingSpaceContract);

            this.contractDAO.insertContract(contract);
            this.spaceManagement.updateSpot(parkingSpaceContract);

        } else if ( contract.getType().compareTo("TM") == 0){
            Date currentDate = new Date();
            contract.setStartDate(currentDate);
            contract.setStatus("AC");
            parkingSpaceContract.setContract(contract);
            contract.setParkingSpace(parkingSpaceContract);
            this.contractDAO.insertContract(contract);
            this.spaceManagement.updateSpot(parkingSpaceContract);
        }

    }

    public void endContract(Contract contract) throws Exception {
        this.validatorsContract(contract);
        if(contract.getStatus().toUpperCase().compareTo("AC") != 0) throw new Exception("ESTADO INVALIDO DEL CONTRATO");
        ParkingSpace parkingSpace = contract.getParkingSpace();

        parkingSpace.setStatus("FR");
        parkingSpace.setContract(null);
        Date currentDate = new Date();
        contract.setEndDate(currentDate);
        contract.setStatus("IN");

        if(contract.getType().toUpperCase().compareTo("MT") == 0){
            this.contractDAO.modifyContract(contract);
            this.spaceManagement.updateSpot(parkingSpace);
        } else if(contract.getType().toUpperCase().compareTo("TM") == 0){
            //FALTA HACER LA TARIFA BIEN PARA IMPLEMENTAR

        }
    }

    public List<Contract> getAllContracts() throws  Exception {
        List<Contract> contracts = this.contractDAO.getContracts();
        contracts.stream().forEach(contract -> contract.getRates().size());
        return contracts;
    }

    public List<Contract> getContractsByIdPerson(int idPerson) throws Exception {
        if(idPerson <= 0) throw new Exception("ID DE PERSONA INVALIDO");
        List<Contract> contracts = this.contractDAO.getContractsByIdPerson(idPerson);
        return contracts;

    }

    private void validatorsContract(Contract contract) throws Exception{
        if(contract == null)  throw new Exception("NO SE ENCONTRO DATOS DE EL CONTRATO");
        if(contract.getType().toUpperCase().compareTo("MT") != 0  && contract.getType().toUpperCase().compareTo("TM") != 0) throw new Exception("NO SE ENCONTRO EL TIPO DE CONTRATO");
        if(contract.getPerson() == null) throw new Exception("SE REQUIRE UNA PERSONA PARA REALIZAR EL CONTRATO");
        if(contract.getAutomobile() == null) throw new Exception("SE NECESITA UN AUTOMOBILE PARA REALIZAR EL CONTRATO");
        if(contract.getParkingSpace() == null) throw new Exception("SE NECESITA UN SPOT PARA REALIZAR EL CONTRATO");
        ParkingSpace parkingSpaceContract = contract.getParkingSpace();
        ParkingSpace parkingSpaceSearched = this.spaceManagement.readSpot(parkingSpaceContract.getIdParkingSpace());
        if(parkingSpaceContract.getLocation().toUpperCase().compareTo(parkingSpaceSearched.getLocation()) != 0) throw new Exception("INCONSISTENCIA DE DATOS EN EL SPOT");
        if(parkingSpaceSearched.getStatus().toUpperCase().compareTo("FR") != 0) throw new Exception("ESTE SPOT YA ESTA OCUPADO");
    }
}