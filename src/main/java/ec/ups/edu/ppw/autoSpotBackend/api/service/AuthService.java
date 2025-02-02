package ec.ups.edu.ppw.autoSpotBackend.api.service;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.request.LoginRequest;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.request.RegisterRequest;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.response.AuthResponse;
import ec.ups.edu.ppw.autoSpotBackend.api.management.AuthManagement;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthService {
    @Inject
    private AuthManagement authService;

    @POST
    @Path("/sign-up")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterRequest registerRequest) {
        AuthResponse  authResponse = this.authService.registerNewAccount(registerRequest);
        return Response.ok(authResponse).build();
    }

    @GET
    @Path("/sign-in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(LoginRequest loginRequest) {
        return Response.ok("Hello world").build();
    }

}
