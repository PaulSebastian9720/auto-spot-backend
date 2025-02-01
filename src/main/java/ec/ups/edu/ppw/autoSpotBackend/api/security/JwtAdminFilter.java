package ec.ups.edu.ppw.autoSpotBackend.api.security;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.AdminOnly;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;


@AdminOnly
@Provider
@Priority(Priorities.AUTHORIZATION)
public class JwtAdminFilter implements ContainerRequestFilter {
    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void filter(ContainerRequestContext requestContext) throws CustomException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) throw  new CustomException(Errors.UNAUTHORIZED, "Fail in Bearer");
        String token = authorizationHeader.substring(7);
        String role = this.jwtTokenProvider.getRoleFromToken(token);
        if (role == null || !role.equals("A")) throw new CustomException(Errors.UNAUTHORIZED, "You do not have the necessary permissions");
    }

}