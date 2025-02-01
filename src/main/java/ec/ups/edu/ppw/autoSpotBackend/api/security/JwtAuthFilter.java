package ec.ups.edu.ppw.autoSpotBackend.api.security;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.util.Errors;
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

    private final List<String> EXCLUDED_PATHS = List.of(
            "/auth/login",
            "/auth/register",
            "/automobiles/1/automobile"
    );

    @Override
    public void filter(ContainerRequestContext requestContext) throws CustomException {

        String path = requestContext.getUriInfo().getPath();
        System.out.println(path);

        if (EXCLUDED_PATHS.contains(path)) return;

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) throw  new CustomException(Errors.UNAUTHORIZED, "Fail in Bearer");

        String token = authorizationHeader.substring(7);

        if (!jwtTokenProvider.validateToken(token)) throw  new CustomException(Errors.UNAUTHORIZED, "This token is not valid");

        String mail = jwtTokenProvider.getMailFromToken(token);
        requestContext.setProperty("mail", mail);
    }
}
