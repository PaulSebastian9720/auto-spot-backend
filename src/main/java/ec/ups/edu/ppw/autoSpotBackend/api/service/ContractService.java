package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.management.ContractManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/contracts")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class ContractService {

    @Inject
    private ContractManagement contractManagement;

    @GET
    @Path("/{idContract}/contract")
    public Response getContract(@PathParam("idContract") int idContract) {
        try {
            Contract contract = this.contractManagement.getContract(idContract);
            return Response.ok(contract).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id_person}/list-for-person")
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
    public Response getAll() {
        try {
            List<Contract> contracts = this.contractManagement.getAllContracts();
            return Response.ok(contracts).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}