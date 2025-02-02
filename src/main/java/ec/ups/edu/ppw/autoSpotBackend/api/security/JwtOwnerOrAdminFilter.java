package ec.ups.edu.ppw.autoSpotBackend.api.security;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.api.management.PersonManagement;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import ec.ups.edu.ppw.autoSpotBackend.util.filter.OwnerOrAdmin;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@OwnerOrAdmin
@Provider
@Priority(Priorities.AUTHORIZATION)
public class JwtOwnerOrAdminFilter implements ContainerRequestFilter {
    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Inject PersonManagement personManagement;

    @Override
    public void filter(ContainerRequestContext requestContext) throws CustomException {
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
//            throw  new CustomException(Errors.UNAUTHORIZED, "Fail in Bearer tokens");
//        String token = authorizationHeader.substring(7);
//        String mail = this.jwtTokenProvider.getMailFromToken(token);
//        if(!this.personManagement.personExistByMail(mail))
//            throw new CustomException(Errors.UNAUTHORIZED, "There is no exist a person with this credentials");
//        String role = this.jwtTokenProvider.getRoleFromToken(token);
//        if(role.equals("A")) return;
//        String userId = requestContext.getUriInfo().getPathParameters().getFirst("personId");
//        try {
//            int idPerson = Integer.parseInt(userId);
//            Person personCredentials = this.personManagement.getPersonById(idPerson);
//            if(!personCredentials.getMail().equals(mail))
//                throw new CustomException(Errors.UNAUTHORIZED,  "The account credentials are not correct.");
//            return;
//        }catch (Exception e) {
//            throw new CustomException(Errors.INTERNAL_SERVER_ERROR, "Error in us server: " + e.getMessage());
//        }
    }
}