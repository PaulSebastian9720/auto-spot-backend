package ec.ups.edu.ppw.autoSpotBackend.service;


import ec.ups.edu.ppw.autoSpotBackend.management.PersonManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/persons")
public class PersonService {
    @Inject
    private PersonManagement personManagement;

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Person person) {
        try {
            personManagement.addPerson(person);
            return Response.ok("DATOS DE LA PERSONA REGISTRADO CORRECTAMENTE").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{person_id}/person")
    @Produces("application/json")
    public Response get(@PathParam("person_id") int person_id) {
        try {
            Person person = personManagement.getPersonById(person_id);
            return Response.ok(person).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update( Person person) {
        try {
            personManagement.updatePerson( person);
            return Response.ok("PERSONA ACTUALIZADA CORRECTAMENTE").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try{
            List<Person> persons = personManagement.getAllPersons();
            return Response.ok(persons).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
