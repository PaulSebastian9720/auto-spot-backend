package ec.ups.edu.ppw.autoSpotBackend.api.service;


import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.management.PersonManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.AdminOnly;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/users")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {
    @Inject
    private PersonManagement personManagement;

    @GET
    @Path("/{idUser}/user")
    public Response getByID(@PathParam("idUser") int userId) {
        UserDTO userDTO = personManagement.getPersonById(userId);
        return Response.ok(userDTO).build();
    }

    @PUT
    @Path("/update")
    public Response update( UserDTO user) {
        personManagement.updatePerson( user);
        return Response.ok(Map.of("message", "Successful user update")).build();

    }

    @GET
    @AdminOnly
    @Path("/getAll")
    public Response getAllPersons() {
        List<UserDTO> persons = personManagement.getAllPersons();
        return Response.ok(persons).build();
    }

    @PUT
    @AdminOnly
    @Path("/change-state/{idUser}")
    public Response changeState(@PathParam("idUser") int userId) {
        String newState = personManagement.changeState(userId);
        return Response.ok(Map.of("message", "Successful The person change state to " + newState)).build();
    }

}