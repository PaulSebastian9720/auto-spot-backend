package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.management.AutomobileManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.rmi.server.ExportException;
import java.util.List;

@Path("/automobiles")
public class AutomovileService {

    @Inject
    private AutomobileManagement automobileManagement;


    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Automobile automobile) {
        try{
            automobileManagement.addAutomobile(automobile);
            return Response.ok("AUTOMOBILE REGISTRADO CORRECTAMENTES").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idAutomobile}/automobile")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("idAutomobile") int idAutomobile) {
        try{
            Automobile automobile = this.automobileManagement.getAutomobileById(idAutomobile);
            return Response.ok(automobile).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Automobile automobile) {
        try {
            this.automobileManagement.updateAutomobile(automobile);
            return Response.ok("AUTOMOBILE ACTUALIZADO CORRECTAMENTE").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @DELETE
    @Path("/delete/{id_automobile}")
    @Produces("application/json")
    public Response delete(@PathParam("id_automobile") int id_automobile) {
        try {
            this.automobileManagement.removeAutomobile(id_automobile);
            return Response.ok("AUTOMOBILE ELIMINADO CORRECTAMENTE").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{person_id}/list-for-person")
    @Produces("application/json")
    public Response getListByIDPerson(@PathParam("person_id") int person_id) {
        try {
            List<Automobile> autmovileList= automobileManagement.getListByIDPerson(person_id);
            return Response.ok(autmovileList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

