package ec.ups.edu.ppw.autoSpotBackend.api.dto.auth;


public class Auth {

    private String mailUser;

    private String password;



    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
