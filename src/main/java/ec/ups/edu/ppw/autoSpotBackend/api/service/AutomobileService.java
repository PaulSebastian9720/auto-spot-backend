package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.AutomobileManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.AdminOnly;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.OwnerOrAdmin;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/automobiles")

@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class AutomobileService {

    @Inject
    private AutomobileManagement automobileManagement;


    @POST
    @Path("/create")
//    @OwnerOrAdmin
    public Response create(Automobile automobile) {
        automobileManagement.addAutomobile(automobile);
        return Response.ok(Map.of("message", "Successful automobile registration")).build();
    }

    @GET
    @Path("/{idAutomobile}/automobile")
    public Response get(@PathParam("idAutomobile") int idAutomobile) {
        Automobile automobile = this.automobileManagement.getAutomobileById(idAutomobile);
        return Response.ok(automobile).build();

    }

    @PUT
    @Path("/update")
//    @OwnerOrAdmin
    public Response update(Automobile automobile) {
        this.automobileManagement.updateAutomobile(automobile);
        return Response.ok(Map.of("message", "Successful automobile update")).build();
    }

    @DELETE
    @AdminOnly
    @Path("/delete/{id_automobile}")
    public Response delete(@PathParam("id_automobile") int id_automobile) {
        this.automobileManagement.removeAutomobile(id_automobile);
        return Response.ok(Map.of("message", "Successful automobile delete")).build();

    }

    @GET
//    @OwnerOrAdmin
    @Path("/{person_id}/list-for-person")
    public Response getListByIDPerson(@PathParam("person_id") int person_id) {
        List<Automobile> autmovileList= automobileManagement.getListByIDPerson(person_id);
        return Response.ok(autmovileList).build();

    }
}

