package ec.ups.edu.ppw.autoSpotBackend.api.service;


import ec.ups.edu.ppw.autoSpotBackend.api.management.PersonManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.AdminOnly;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/persons")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class PersonService {
    @Inject
    private PersonManagement personManagement;

    @POST
    @AdminOnly
    @Path("/create")
    public Response create(Person person) {
        personManagement.addPerson(person);
        return Response.ok("Successful all person's date registration").build();
    }

    @GET
    @Path("/{person_id}/person")
    public Response getByID(@PathParam("person_id") int person_id) {
        Person person = personManagement.getPersonById(person_id);
        return Response.ok(person).build();
    }

    @PUT
    @Path("/update")
    public Response update( Person person) {
        personManagement.updatePerson( person);
        return Response.ok("Successful person update").build();

    }

    @GET
    @AdminOnly
    @Path("/getAll")
    public Response getAllPersons() {
        List<Person> persons = personManagement.getAllPersons();
        return Response.ok(persons).build();
    }

    @PUT
    @AdminOnly
    @Path("/change-state/{idPerson}")
    public Response changeState(@PathParam("idPerson") int idPerson) {
        String newState = personManagement.changeState(idPerson);
        return Response.ok("Successful The person change state to " + newState).build();
    }

}