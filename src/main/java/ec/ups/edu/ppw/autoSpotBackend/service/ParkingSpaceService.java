package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/parkingSpaces")
public class ParkingSpaceService {

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ParkingSpace parkingSpace) {
        return null;
    }

    @GET
    @Path("/{id_ParkingSpace}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(String rate_id) {
        return null;
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ParkingSpace parkingSpace) {
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
    public Response delete(@QueryParam("id_ParkingSpace") String id_ParkingSpace) {
        return null;
    }
}




