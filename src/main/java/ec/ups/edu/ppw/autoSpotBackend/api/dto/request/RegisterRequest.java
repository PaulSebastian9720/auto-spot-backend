package ec.ups.edu.ppw.autoSpotBackend.api.dto.request;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.Auth;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank(message = "The data to the authentication is required")
    private Auth credentials;
    private UserDTO user;
    public RegisterRequest(){
    }

    public Auth getCredentials() {
        return credentials;
    }

    public void setCredentials(Auth credentials) {
        this.credentials = credentials;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
