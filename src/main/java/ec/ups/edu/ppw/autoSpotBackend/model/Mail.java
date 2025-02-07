package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SPOT_MAIL")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mai_id")
    private int idMail;

    @Column(name = "mai_mail")
    private String mail;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdMail() {
        return idMail;
    }

    public void setIdMail(int idMail) {
        this.idMail = idMail;
    }

}
