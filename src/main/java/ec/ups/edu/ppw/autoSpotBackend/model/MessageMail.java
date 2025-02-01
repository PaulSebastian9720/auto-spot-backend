package ec.ups.edu.ppw.autoSpotBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Table(name = "SPOT_MESSAGE_MAIL")
public class MessageMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mes_mail_id")
    private int idMessageMail;

    @Column(name = "mes_mail_mailFrom", nullable = false)
    private String mailFrom;

    @Column(name = "mes_mail_nameFrom")
    private String nameFrom;

    @Column(name = "mes_mail_mailDestination", nullable = false)
    private String mailDestination;

    @Column(name = "mes_mail_header")
    private String header;

    @Column(name = "mes_mail_shippingDate")
    private Date shippingDate;

    @Column(name = "mes_mail_status", nullable = false)
    @Pattern(regexp = "RD|NR", message = "El status debe ser 'RD', 'NR'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_mess_id", nullable = false)
    @JsonBackReference
    private Person person;

    public MessageMail() {
    }

    public int getIdMessageMail() {
        return idMessageMail;
    }

    public void setIdMessageMail(int idMessageMail) {
        this.idMessageMail = idMessageMail;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getMailDestination() {
        return mailDestination;
    }

    public void setMailDestination(String mailDestination) {
        this.mailDestination = mailDestination;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public @Pattern(regexp = "RD|NR", message = "El status debe ser 'RD', 'NR'") String getStatus() {
        return status;
    }

    public void setStatus(@Pattern(regexp = "RD|NR", message = "El status debe ser 'RD', 'NR'") String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonbProperty("personId")
    public Integer getPersonId() {
        return person != null ? person.getIdPerson() : null;
    }

    @JsonbProperty("personId")
    public void setPersonId(Integer personId) {
        if (personId == null) return;
        this.person = new Person();
        this.person.setIdPerson(personId);
    }

}
