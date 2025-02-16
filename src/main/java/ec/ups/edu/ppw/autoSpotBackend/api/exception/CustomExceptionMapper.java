package ec.ups.edu.ppw.autoSpotBackend.api.exception;

import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {

    private static final Map<String, Response.Status> ERROR_TO_STATUS = new HashMap<>();

    static {
        ERROR_TO_STATUS.put(Errors.UNAUTHORIZED, Response.Status.UNAUTHORIZED);
        ERROR_TO_STATUS.put(Errors.BAD_REQUEST, Response.Status.BAD_REQUEST);
        ERROR_TO_STATUS.put(Errors.NOT_FOUND, Response.Status.NOT_FOUND);
        ERROR_TO_STATUS.put(Errors.INVALID_CREDENTIALS, Response.Status.FORBIDDEN);
    }

    @Override
    public Response toResponse(CustomException exception) {
        Response.Status status = ERROR_TO_STATUS.getOrDefault(exception.getErrorCode(), Response.Status.INTERNAL_SERVER_ERROR);
        String message = exception.getMessage();
        return Response.status(status)
                .entity(message)
                .build();
    }
}

