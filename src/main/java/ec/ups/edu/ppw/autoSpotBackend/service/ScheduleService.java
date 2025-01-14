package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.model.Schedule;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/schedule")
public class ScheduleService {

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createException(Schedule schedule) {
        return null;
    }

    @GET
    @Path("/{schedule_id}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(String schedule_id) {
        return null;
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Schedule schedule) {
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
    public Response deleteException (@QueryParam("id_day") String id) {
        return null;
    }

}