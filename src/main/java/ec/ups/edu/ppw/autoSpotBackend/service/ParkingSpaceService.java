package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.dao.ParkingSpaceDAO;
import ec.ups.edu.ppw.autoSpotBackend.management.SpaceManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/parkingSpaces")
public class ParkingSpaceService {

    @Inject
    private SpaceManagement spaceManagement;

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ParkingSpace parkingSpace) {
        try {
            this.spaceManagement.addSpot(parkingSpace);
            return Response.ok("SPOT CREADO CORRECTAMENTE").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id_ParkingSpace}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(int id_ParkingSpace) {
        try {
            ParkingSpace space = this.spaceManagement.readSpot(id_ParkingSpace);
            return Response.ok(space).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
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
        try{
            List<ParkingSpace> listSpaces = this.spaceManagement.getAllSpaces();
            return Response.ok(listSpaces).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id_ParkingSpace") String id_ParkingSpace) {
        return null;
    }
}




