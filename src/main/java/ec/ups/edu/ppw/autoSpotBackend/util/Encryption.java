package ec.ups.edu.ppw.autoSpotBackend.util;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Encryption {

    public static String hashPassword(String password) throws CustomException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(Errors.INTERNAL_SERVER_ERROR, "Error in credential management");
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        return hashPassword(inputPassword).equals(storedHash);
    }

    public static String generateTicket() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();

        return "" +
                letters.charAt(random.nextInt(letters.length())) +
                letters.charAt(random.nextInt(letters.length())) +
                random.nextInt(10) +
                random.nextInt(10) + "-" +
                letters.charAt(random.nextInt(letters.length())) +
                letters.charAt(random.nextInt(letters.length())) +
                random.nextInt(10) +
                random.nextInt(10);
    }

}
