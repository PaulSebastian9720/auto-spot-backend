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
            this.spaceManagement.addSpot(parkingSpace);
            return Response.ok("SPOT CREADO CORRECTAMENTE").build();
    }

    @GET
    @Path("/{id_ParkingSpace}/space")
    public Response get(@PathParam("id_ParkingSpace")  int id_ParkingSpace) {
            ParkingSpace space = this.spaceManagement.readSpot(id_ParkingSpace);
            return Response.ok(space).build();
    }

    @PUT
    @Path("/change-state/{idSpace}")
    public Response changeState(@PathParam("idSpace") int idSpace) {
            this.spaceManagement.changeState(idSpace);
            return Response.ok("ESTADO CAMBIADO CORRECTAMENTE").build();
    }


    @GET
    @Path("/getAll")
    public Response getAll() {
            List<ParkingSpace> listSpaces = this.spaceManagement.getAllSpaces();
            return Response.ok(listSpaces).build();
    }

}




