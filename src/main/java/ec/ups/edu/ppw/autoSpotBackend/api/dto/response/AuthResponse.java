package ec.ups.edu.ppw.autoSpotBackend.api.dto.response;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;

public class AuthResponse {

    private UserDTO user;
    private JwtResponse jwt;

    public AuthResponse() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public JwtResponse getJwt() {
        return jwt;
    }

    public void setJwt(JwtResponse jwt) {
        this.jwt = jwt;
    }
}
