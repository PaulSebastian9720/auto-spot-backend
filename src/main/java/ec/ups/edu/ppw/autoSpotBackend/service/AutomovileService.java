package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/automoviles")
public class AutomovileService {

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Automobile automobile) {
        return null;
    }

    @GET
    @Path("/{idAutomobile}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(String idAutomobile) {
        return null;
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Automobile automobile) {
        return null;
    }

    @GET
    @Path("/getAll")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return null;
    }

    @DELETE
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("idAutomobile") String idAutomobile) {
        return null;
    }
}

