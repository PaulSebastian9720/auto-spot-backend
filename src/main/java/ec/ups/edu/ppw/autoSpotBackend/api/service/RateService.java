package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.RateManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Rate;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

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
        Rate rate  = this.rateManagement.getRateById(rate_id);
        return Response.ok(rate).build();

    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    public Response update(Rate rate) {
        this.rateManagement.updateRate(rate);
        return Response.ok(Map.of("message", "Successful rate update")).build();
    }

    @GET
    @Path("/getAll")
    public Response getAll() {
        List<Rate> results = this.rateManagement.getAllRates();
        return Response.ok(results).build();
    }

}


