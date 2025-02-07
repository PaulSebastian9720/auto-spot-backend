package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.Auth;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.request.LoginRequest;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.request.RegisterRequest;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.response.AuthResponse;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.response.JwtResponse;
import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.api.security.JwtTokenProvider;
import ec.ups.edu.ppw.autoSpotBackend.dao.MailDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Mail;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import ec.ups.edu.ppw.autoSpotBackend.util.Encryption;
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

     @Inject
     private MailDAO mailDAO;

     @Transactional
     public AuthResponse registerNewAccount(RegisterRequest request) throws CustomException {
         Auth auth = request.getCredentials();

         if(auth == null) throw new CustomException(Errors.BAD_REQUEST, "The credentials are required");
         this.validatorsPattern(auth.getMailUser(), auth.getPassword());
         if(request.getUser() == null) throw new
                 CustomException(Errors.BAD_REQUEST, "No user information found");
         if(request.getUser().getName() == null || request.getUser().getName().isEmpty())
             throw new CustomException(Errors.BAD_REQUEST, "At least the username is required");

         Mail mail = mailDAO.getMailByEmail(auth.getMailUser());
         Person newPerson;

         if( mail != null ){
             if(mail.isLinkedAccount() )
                 throw new CustomException(Errors.BAD_REQUEST,"The email is already registered with a account");

             newPerson = this.personManagement.getPersonByMail(auth.getMailUser());
             newPerson.setName(request.getUser().getName());
             newPerson.setLastName(request.getUser().getLastName());
             newPerson.setBirthDay(request.getUser().getBirthDay());
         }
         else {
             mail = new Mail();
             newPerson = Person.fromUserDTO(request.getUser());
         }

         final String passwordHash = Encryption.hashPassword(auth.getPassword());
         mail.setMail(auth.getMailUser());
         mail.setLinkedAccount(true);
         newPerson.setMailUser(mail);
         newPerson.setStatus("A");
         newPerson.setRole("C");
         newPerson.setPassword(passwordHash);
         if (newPerson.getIdPerson() == 0) this.personManagement.addPerson(newPerson);
         else this.personManagement.updatePerson(UserDTO.fromPersonModel(newPerson));

         Person personRegister = this.personManagement.getPersonByMail(newPerson.getMailUser().getMail());
         if(personRegister == null)
             throw new CustomException(Errors.INTERNAL_SERVER_ERROR, "There was a failure in the registration of the User");

         JwtResponse token = new JwtResponse();
         token.setToken(jwtTokenProvider.createToken(personRegister.getMailUser().getMail(), personRegister.getName(), personRegister.getRole()));
         AuthResponse authResponse = new AuthResponse();
         authResponse.setUser(UserDTO.fromPersonModel(personRegister));
         authResponse.setJwt(token);

         return authResponse;
     }

     public AuthResponse signIn(LoginRequest loginRequest) throws CustomException{
         Auth auth = loginRequest.getCredentials();
         if(auth == null)  throw  new CustomException(Errors.BAD_REQUEST, "Invalid login request, cannot data not null");
         if(auth.getPassword() == null || auth.getMailUser() == null) throw new CustomException(Errors.BAD_REQUEST, "Mail or Password are incorrect");
         Person person = personManagement.getPersonByMail(auth.getMailUser());
         if(person == null) throw new CustomException(Errors.BAD_REQUEST, "Mail or Password are incorrect");
         if(!Encryption.verifyPassword(auth.getPassword(), person.getPassword())) throw new CustomException(Errors.BAD_REQUEST, "Mail or Password are incorrect");
         if(person.getStatus().equals("I")) throw new CustomException(Errors.BAD_REQUEST, "This user has a invalid account");
         JwtResponse token = new JwtResponse();
         token.setToken(jwtTokenProvider.createToken(person.getMailUser().getMail(), person.getName(), person.getRole()));
         UserDTO user = UserDTO.fromPersonModel(person);
         AuthResponse authResponse = new AuthResponse();
         authResponse.setUser(user);
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