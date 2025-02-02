package ec.ups.edu.ppw.autoSpotBackend.util.validator;

import java.util.regex.Pattern;

public class ValidatorPattern {
    public static boolean isValidPassword(String password) {
        if (password.isEmpty()) return false;
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$";
        return Pattern.matches(passwordRegex, password);
    }

    public static boolean isValidEmail(String email) {
        if (email.isEmpty())  return false;
        String emailRegex = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);
    }

}
