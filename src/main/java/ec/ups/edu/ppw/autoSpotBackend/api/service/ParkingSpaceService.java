package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.SpaceManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/parkingSpaces")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingSpaceService {

    @Inject
    private SpaceManagement spaceManagement;

    @POST
    @Path("/create")
    public Response create(ParkingSpace parkingSpace) {
        try {
            this.spaceManagement.addSpot(parkingSpace);
            return Response.ok("SPOT CREADO CORRECTAMENTE").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id_ParkingSpace}/space")
    public Response get(@PathParam("id_ParkingSpace")  int id_ParkingSpace) {
        try {
            ParkingSpace space = this.spaceManagement.readSpot(id_ParkingSpace);
            return Response.ok(space).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/change-state/{idSpace}")
    public Response changeState(@PathParam("idSpace") int idSpace) {
        try {
            this.spaceManagement.changeState(idSpace);
            return Response.ok("ESTADO CAMBIADO CORRECTAMENTE").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/getAll")
    public Response getAll() {
        try{
            List<ParkingSpace> listSpaces = this.spaceManagement.getAllSpaces();
            return Response.ok(listSpaces).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}




