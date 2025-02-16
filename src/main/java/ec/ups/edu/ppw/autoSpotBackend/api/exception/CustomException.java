package ec.ups.edu.ppw.autoSpotBackend.api.exception;

public class CustomException extends RuntimeException {
    private final String errorCode;
    private final String message;

    public CustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = errorCode + ": " + message;
        System.err.println(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}

