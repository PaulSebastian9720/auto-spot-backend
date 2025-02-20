package ec.ups.edu.ppw.autoSpotBackend.api.security;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthFilter implements ContainerRequestFilter {
    @Inject
    private JwtTokenProvider jwtTokenProvider;

    private static  final List<String> EXCLUDED_PATHS = List.of(
            "/auth/sign-in",
            "/auth/sign-up",
            "/comments/getAll",
            "/comments/create",
            "/schedules/getAll"
    );

    @Override
    public void filter(ContainerRequestContext requestContext) throws CustomException {
        String path = requestContext.getUriInfo().getPath();
        if (EXCLUDED_PATHS.contains(path)) return;
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println(authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) throw  new CustomException(Errors.UN_AUTHORIZED, "BAD FORMAT THE HEADER");
        String token = authorizationHeader.substring(7);
        if (!jwtTokenProvider.validateToken(token)) throw  new CustomException(Errors.UN_AUTHORIZED, "THIS TOKEN IS NO VALID");
        if(jwtTokenProvider.isTokenExpired(token)) throw new CustomException(Errors.UN_AUTHORIZED, "THIS TOKEN IS EXPIRED");
        String mail = jwtTokenProvider.getMailFromToken(token);
        requestContext.setProperty("mail", mail);
    }
}
