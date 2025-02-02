package ec.ups.edu.ppw.autoSpotBackend.api.dto.request;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.Auth;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank(message = "The data to the authentication is required")
    private Auth authData;
    private UserDTO user;
    public RegisterRequest(){
    }

    public Auth getAuthData() {
        return authData;
    }

    public void setAuthData(Auth authData) {
        this.authData = authData;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
