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
    public Response register(RegisterRequest registerRequest) {
        AuthResponse  authResponse = this.authService.registerNewAccount(registerRequest);
        return Response.ok(authResponse).build();
    }

    @POST
    @Path("/sign-in")
    public Response signIn(LoginRequest loginRequest) {
        AuthResponse authResponse = this.authService.signIn(loginRequest);
        return Response.ok(authResponse).build();
    }

}
