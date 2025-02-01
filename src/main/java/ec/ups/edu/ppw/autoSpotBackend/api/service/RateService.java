package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.RateManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Rate;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/rates")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class RateService {

    @Inject
    private RateManagement rateManagement;

    @GET
    @Path("/{rate_id}/rate")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("rate_id")  int rate_id){
        try {
            Rate rate  = this.rateManagement.getRateById(rate_id);
            return Response.ok(rate).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    public Response update(Rate rate) {
        try {
            this.rateManagement.updateRate(rate);
            return Response.ok("TARIFRA ACTUALIZADA CORRECTAMENTE").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getAll")
    public Response getAll() {
        try {
            List<Rate> results = this.rateManagement.getAllRates();
            return Response.ok(results).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}


