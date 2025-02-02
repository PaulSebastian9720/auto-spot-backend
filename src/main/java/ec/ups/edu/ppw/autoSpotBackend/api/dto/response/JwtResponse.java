package ec.ups.edu.ppw.autoSpotBackend.api.dto.response;

public class JwtResponse {

    private String token;
    private final String tokenType = "Bearer";
    private Long expiresIn;

    public JwtResponse() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
