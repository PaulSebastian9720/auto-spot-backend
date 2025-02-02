package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.MessageMailManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/messages")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class MessageMailService {
    @Inject
    private MessageMailManagement messageMailManagement;

    @POST
    @Path("/create")
    public Response createMessageMail(MessageMail messageMail) {
        this.messageMailManagement.addMessageMail(messageMail);
        return Response.ok().build();
    }

    @PUT
    @Path("/change-state/{idMessageMail}")
    public Response updateMessageMailState(@PathParam("idMessageMail") int idMessageMail) {
        this.messageMailManagement.updateMessageMailStatus(idMessageMail);
        return Response.ok().build();
    }

    @GET
    @Path("/{person_id}/mails")
    public Response getLostByIDPerson(@PathParam("person_id") int person_id) {
        List<MessageMail>  messageMails= messageMailManagement.getMessageMailsPerPerson(person_id);
        return Response.ok(messageMails).build();
    }
}
