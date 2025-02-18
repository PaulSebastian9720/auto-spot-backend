package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqDealBaseDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.TicketDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.model.Ticket;
import ec.ups.edu.ppw.autoSpotBackend.util.Encryption;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TicketManagement {
    @Inject
    private TicketDAO ticketDAO;
    @Inject
    private SpaceManagement spaceManagement;
    @Inject
    private PersonManagement personManagement;
    @Inject
    private AutomobileManagement automobileManagement;
    @Inject
    private RateManagement rateManagement;

    @Transactional
    public String addTicket(ReqDealBaseDTO reqDealBaseDTO) throws CustomException {

        Person person = this.personManagement.getPerson(
                reqDealBaseDTO.getPerson().getDocumentID()
                ,reqDealBaseDTO.getPerson().getIdPerson()
        );
        Automobile automobile = this.automobileManagement.getAndCreateAutomobile(
                reqDealBaseDTO.getAutomobile().getLicensePlate(),
                reqDealBaseDTO.getAutomobile().getIdAutomobile(),
                person.getIdPerson()
        );
        ParkingSpace parkingSpace = this.spaceManagement.getParkingSpace(
                reqDealBaseDTO.getParkingSpace().getLocation(),
                reqDealBaseDTO.getParkingSpace().getIdParkingSpace()
        );

        if(!parkingSpace.getStatus().equalsIgnoreCase("FR"))
            throw new CustomException(Errors.BAD_REQUEST,"The parking space with this location is not available");

        Ticket ticket = new Ticket();
        ticket.setAutomobile(automobile);
        ticket.setPerson(person);
        ticket.setParkingSpace(parkingSpace);
        ticket.setStartDate(new Date());
        ticket.setStatus("AC");
        ticket.setFinalPrice(0.0);
        final String accessTicket = Encryption.generateTicket();
        ticket.setAccessTicket(accessTicket);
        this.ticketDAO.insertTicket(ticket);

        Ticket ticketSearch = this.ticketDAO.
                getTicketByLocation(parkingSpace.getLocation());

        parkingSpace.setStatus("BT");
        parkingSpace.setDealBase(ticketSearch);
        this.spaceManagement.updateParkingSpace(parkingSpace);
        return ticketSearch.getAccessTicket();
    }

    @Transactional
    public void cancelTicket(String accessTicket) throws CustomException {
        if(accessTicket == null || accessTicket.isEmpty())
            throw new CustomException(Errors.BAD_REQUEST, "The access ticket is required");
        Ticket ticket = this.ticketDAO.getTicketByAccessTicket(accessTicket);
        if (ticket == null)
            throw new CustomException(Errors.NOT_FOUND, "The access ticket not found");
        if(!ticket.getStatus().equalsIgnoreCase("AC"))
            throw new CustomException(Errors.BAD_REQUEST,"This ticket is not active");
        ticket.setStatus("CL");
        ParkingSpace parkingSpace = this.spaceManagement.getParkingSpace(
                "",
                ticket.getParkingSpace().getIdParkingSpace()
        );
        ticket.setFinalPrice(0);
        ticket.setEndDate(new Date());
        parkingSpace.setStatus("FR");
        parkingSpace.setDealBase(null);
        this.ticketDAO.modifyTicket(ticket);
        this.spaceManagement.updateParkingSpace(parkingSpace);
    }

    public Ticket getTicketByAccessTicket(String accessTicket) throws CustomException{
        if(accessTicket == null || accessTicket.isEmpty())
            throw new CustomException(Errors.BAD_REQUEST, "The access ticket is required");
        Ticket ticket = this.ticketDAO.getTicketByAccessTicket(accessTicket);
        if (ticket == null)
            throw new CustomException(Errors.NOT_FOUND, "The access ticket not found");
        return ticket;
    }

    public void endTicket(){

    }

    public List<Ticket> getAllTickets() {
        return this.ticketDAO.getTicketList();
    }

    public Ticket getTicketByID(int idTicket) throws CustomException{
        if(idTicket <= 0)
            throw new CustomException(Errors.BAD_REQUEST, "The ticket ID is required");
        Ticket ticket = this.ticketDAO.getTicketById(idTicket);
        if(ticket == null)
            throw new CustomException(Errors.NOT_FOUND,"Not found Ticket whit this ID");
        return ticket;
    }

    public List<Ticket> getTicketsByIdPerson(int idPerson)throws  CustomException{
        if(idPerson <= 0)
            throw new CustomException(Errors.BAD_REQUEST, "Invalid id User");
        List<Ticket> tickets = this.ticketDAO.getTicketsByIdPerson(idPerson);
        return tickets;

    }

    public Ticket getTicketByLocation(String location) throws CustomException{
        if(location == null || location.isEmpty())
            throw  new CustomException(Errors.BAD_REQUEST, "The location parameter is required");
        Ticket ticket = this.ticketDAO.getTicketByLocation(location);
        if(ticket == null) throw new CustomException(Errors.NOT_FOUND,"Not found Ticket whit this location");
        return ticket;
    }

}
