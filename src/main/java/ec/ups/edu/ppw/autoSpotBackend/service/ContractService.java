package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.management.ContractManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contracts")
public class ContractService {

    @Inject
    private ContractManagement contractManagement;

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Contract contract) {
        try {
            this.contractManagement.createContract(contract);
            return Response.ok("CONTRATO CREADO CORRECTAMENTE").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/end-contract")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response endContract(Contract contract) {
        try {
            contractManagement.endContract(contract);
            return Response.ok("CONTRATO TERMINADO CORRECTAMENTE").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/getAll")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return null;
    }
}


