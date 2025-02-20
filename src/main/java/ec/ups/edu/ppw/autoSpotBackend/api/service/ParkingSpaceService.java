package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ParkingSpaceDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqSpaceDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.management.SpaceManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/parkingSpaces")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class ParkingSpaceService {

    @Inject
    private SpaceManagement spaceManagement;

    @POST
    @Path("/create")
    public Response create(ReqSpaceDTO newSpace) {
        this.spaceManagement.addSpot(newSpace);
        return Response.ok(Map.of("message", "Successful Spot/s Register")).build();
    }

    @GET
    @Path("/{id_ParkingSpace}/space")
    public Response get(@PathParam("id_ParkingSpace")  int id_ParkingSpace) {
            ParkingSpace space = this.spaceManagement.readSpot(id_ParkingSpace);
            return Response.ok(Map.of("message", "Successful Parking Space new Location insert  ")).build();
    }

    @GET
    @Path("/{status}/filterList")
    public Response filterByStatus(@PathParam("status") String status) {
        List<ParkingSpace> listSpaces = this.spaceManagement.getListPerStatus(status);
        return Response.ok(listSpaces).build();
    }

    @PUT
    @Path("/change-state/{idSpace}")
    public Response changeState(@PathParam("idSpace") int idSpace) {
            this.spaceManagement.changeState(idSpace);
            return Response.ok(Map.of("message", "Successful change Status")).build();
    }


    @GET
    @Path("/getAll")
    public Response getAll() {
        List<ParkingSpaceDTO> listSpaces = this.spaceManagement.getAllParkingSpace();
        return Response.ok(listSpaces).build();
    }

}




