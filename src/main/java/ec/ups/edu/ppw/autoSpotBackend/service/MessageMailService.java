package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.management.MessageMailManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/messageMails")
public class MessageMailService {
    @Inject
    private MessageMailManagement messageMailManagement;

    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createMessageMail(MessageMail messageMail) {
        try {
            this.messageMailManagement.addMessageMail(messageMail);
            return Response.ok("MENSAJE ENVIADO CORRECTAMENTE").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/change-state/{idMessageMail}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMessageMailState(@PathParam("idMessageMail") int idMessageMail) {
        try {
            this.messageMailManagement.updateMessageMailStatus(idMessageMail);
            return Response.ok("ESTADO DEL MENSAJE CAMBIO").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{person_id}/mails")
    @Produces("application/json")
    public Response getLostByIDPerson(@PathParam("person_id") int person_id) {
        try {
            List<MessageMail>  messageMails= messageMailManagement.getMessageMailsPerPerson(person_id);
            return Response.ok(messageMails).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
