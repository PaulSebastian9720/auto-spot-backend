package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SPOT_MAIL")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mai_id")
    private int idMail;

    @Column(name = "mai_mail", nullable = false, unique = true, updatable = false)
    private String mail;

    @Column(name = "mai_likedAccounts", nullable = false)
    private boolean isLinkedAccount;



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdMail() {
        return idMail;
    }

    public boolean isLinkedAccount() {
        return isLinkedAccount;
    }

    public void setLinkedAccount(boolean linkedAccount) {
        isLinkedAccount = linkedAccount;
    }

    public void setIdMail(int idMail) {
        this.idMail = idMail;
    }

}
