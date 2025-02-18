package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqDealBaseDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.management.TicketManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Ticket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/tickets")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class TicketService {

    @Inject
    private TicketManagement ticketManagement;

    @GET
    @Path("/{idTicket}/ticket")
    public Response getOneTicketById(@PathParam("idTicket") int idTicket ){
        Ticket ticket =this.ticketManagement.getTicketByID(idTicket);
        return Response.ok(ticket).build();
    }

    @GET
    @Path("/{idPerson}/list-for-person")
    public Response getTicketsByPersonId(@PathParam("idPerson") int idPerson){
        List<Ticket> tickets = this.ticketManagement.getTicketsByIdPerson(idPerson);
        return Response.ok(tickets).build();
    }

    @GET
    @Path("/getAll")
    public Response getAllContracts(){
        List<Ticket> tickets = this.ticketManagement.getAllTickets();
        return Response.ok(tickets).build();
    }

    @POST
    @Path("create")
    public Response createTicket(ReqDealBaseDTO reqDealBaseDTO){
        String accessToken = this.ticketManagement.addTicket(reqDealBaseDTO);
        return Response.ok(Map.of("accessToken", accessToken)).build();
    }

    @PUT
    @Path("/{accessTicket}/cancel-ticket")
    public Response cancelTicket(@PathParam("accessTicket") String accessTicket){
        this.ticketManagement.cancelTicket(accessTicket);
        return Response.ok().build();
    }


}
