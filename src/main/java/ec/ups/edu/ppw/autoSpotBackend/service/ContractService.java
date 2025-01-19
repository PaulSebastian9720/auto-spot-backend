package ec.ups.edu.ppw.autoSpotBackend.service;

import ec.ups.edu.ppw.autoSpotBackend.management.ContractManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/contracts")
public class ContractService {

    @Inject
    private ContractManagement contractManagement;

    @GET
    @Path("/{idContract}/contract")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("idContract") int idContract) {
        try {
            Contract contract = this.contractManagement.getContract(idContract);
            return Response.ok(contract).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id_person}/list-for-person")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getContractsByIdPerson(@PathParam("id_person") int idPerson) {
        try {
            List<Contract> contracts = this.contractManagement.getContractsByIdPerson(idPerson);
            return Response.ok(contracts).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

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
        try {
            List<Contract> contracts = this.contractManagement.getAllContracts();
            return Response.ok(contracts).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}