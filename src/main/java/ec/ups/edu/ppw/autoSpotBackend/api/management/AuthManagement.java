package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.Auth;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.request.RegisterRequest;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.response.AuthResponse;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.response.JwtResponse;
import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.api.security.JwtTokenProvider;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import ec.ups.edu.ppw.autoSpotBackend.util.validator.ValidatorPattern;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class AuthManagement {
    @Inject
    private PersonManagement personManagement;

     @Inject
     private JwtTokenProvider jwtTokenProvider;

     @Transactional
     public AuthResponse registerNewAccount(RegisterRequest request) throws CustomException {
         Auth auth = request.getAuthData();
         this.validatorsPattern(auth.getMailUser(), auth.getPassword());
         if(personManagement.personExistByMail(auth.getMailUser()))
             throw  new CustomException(Errors.BAD_REQUEST, "The email is already registered. Please enter a different one");
         if(request.getUser() == null) throw new
                 CustomException(Errors.BAD_REQUEST, "No user information found\n");
         if(request.getUser().getName() == null || request.getUser().getName().isEmpty())
             throw new CustomException(Errors.BAD_REQUEST, "At least the username is required");

         Person newPerson = Person.fromUserDTO(request.getUser());
         newPerson.setName(request.getUser().getName());
         newPerson.setMail(auth.getMailUser());
         newPerson.setStatus("A");
         newPerson.setRole("C");
         newPerson.setPassword(auth.getPassword());
         this.personManagement.addPerson(newPerson);

         Person personRegister = this.personManagement.getPersonByMail(newPerson.getMail());
         if(personRegister == null)
             throw new CustomException(Errors.INTERNAL_SERVER_ERROR, "There was a failure in the registration of the User");

         JwtResponse token = new JwtResponse();
         token.setToken(jwtTokenProvider.createToken(personRegister.getMail(), personRegister.getName(), personRegister.getRole()));
         AuthResponse authResponse = new AuthResponse();
         authResponse.setUser(UserDTO.fromPersonModel(personRegister));
         authResponse.setJwt(token);

         return authResponse;
     }

     private void validatorsPattern(String mail, String password) throws CustomException{
         if(mail.isEmpty() && password.isEmpty())
             throw  new CustomException(Errors.UNAUTHORIZED, "The email or password cannot be empty");
         if(!ValidatorPattern.isValidEmail(mail))
             throw new CustomException(Errors.BAD_REQUEST, "The email does not meet one's standards");
         if(!ValidatorPattern.isValidPassword(password))
             throw new CustomException(Errors.BAD_REQUEST, "The password must be between 8 and 20 characters, " +
                     "contain at least one uppercase letter, one lowercase letter, one number, and one special character");
     }

}