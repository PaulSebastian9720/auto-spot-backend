package ec.ups.edu.ppw.autoSpotBackend.api.dto.request;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.Auth;

public class LoginRequest {
    private Auth credentials;

    public LoginRequest() {
    }

    public Auth getCredentials() {
        return credentials;
    }

    public void setCredentials(Auth credentials) {
        this.credentials = credentials;
    }
}
