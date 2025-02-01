package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.ScheduleManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Schedule;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/schedules")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleService {
    @Inject
    private ScheduleManagement scheduleManagement;
    @POST
    @Path("/create")
    public Response createException(Schedule schedule) {
        try {
            scheduleManagement.addSchedule(schedule);
            return Response.ok("DATOS DE EL HORARIO REGISTRADO CORRECTAMENTE").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{schedule_id}/schedule")
    public Response getByID(@PathParam("schedule_id") int schedule_id) {
        try {
            Schedule schedule = scheduleManagement.readSchedule(schedule_id);
            return Response.ok(schedule).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    public Response update(Schedule schedule) {
        try {
            scheduleManagement.updateSchedule( schedule);
            return Response.ok("HORARIO ACTUALIZADO CORRECTAMENTE").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getAll")
    public Response getAll() {
        try{
            List<Schedule> shedules = scheduleManagement.getAllSchedules();
            return Response.ok(shedules).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/remove")
    public Response deleteException (@QueryParam("schedule_id") int schedule_id) {
        try{
            this.scheduleManagement.removeSchedule(schedule_id);
            return Response.ok("HORARIO ELIMINADO CORRECTAMENTE").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}