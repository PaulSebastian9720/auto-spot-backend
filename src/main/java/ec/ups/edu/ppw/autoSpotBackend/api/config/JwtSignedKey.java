package ec.ups.edu.ppw.autoSpotBackend.api.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class JwtSignedKey {

    private String signedKey;
    private long validityInMilliseconds;

    @PostConstruct
    public void init() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("No se encontró el archivo application.properties");
                return;
            }
            Properties prop = new Properties();
            prop.load(input);
            this.signedKey = prop.getProperty("jwt.secret");
            this.validityInMilliseconds = Long.parseLong(prop.getProperty("jwt.expiration"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getSignedKey() {
        return signedKey;
    }

    public long getValidityInMilliseconds() {
        return validityInMilliseconds;
    }
}