package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqDealBaseDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.api.management.ContractManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.AdminOnly;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/contracts")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class ContractService {

    @Inject
    private ContractManagement contractManagement;

    @GET
    @Path("/{idContract}/contract")
    public Response getContract(@PathParam("idContract") int idContract) throws CustomException {
        Contract contract = this.contractManagement.getContract(idContract);
        return Response.ok(contract).build();
    }

    @GET
    @Path("/{id_person}/list-for-person")
    public Response getContractsByIdPerson(@PathParam("id_person") int idPerson) {
        List<Contract> contracts = this.contractManagement.getContractsByIdPerson(idPerson);
        return Response.ok(contracts).build();

    }

    @POST
    @Path("/create")
    public Response create(ReqDealBaseDTO contractDTO) {
        this.contractManagement.createContract(contractDTO);
        return Response.ok(Map.of("message", "Successful contract register")).build();

    }

    @AdminOnly
    @PUT
    @Path("{idContract}/end-contract")
    public Response endContract(@PathParam("idContract") int idContract) {
        contractManagement.endContract(idContract);
        return Response.ok(Map.of("message", "Successful completion of the contract")).build();

    }

    @AdminOnly
    @PUT
    @Path("{idContract}/cancel-contract")
    public Response cancelContract(@PathParam("idContract") int idContract) {
        contractManagement.cancelContract(idContract);
        return Response.ok(Map.of("message", "Successful cancellation of the contract")).build();
    }

    @AdminOnly
    @GET
    @Path("/getAll")
    public Response getAll() {
        List<Contract> contracts = this.contractManagement.getAllContracts();
        return Response.ok(contracts).build();
    }
}