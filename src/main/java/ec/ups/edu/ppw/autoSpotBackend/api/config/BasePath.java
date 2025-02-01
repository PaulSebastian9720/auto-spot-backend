package ec.ups.edu.ppw.autoSpotBackend.api.config;

import ec.ups.edu.ppw.autoSpotBackend.api.security.JwtAuthFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api/v1")
public class BasePath extends Application { }