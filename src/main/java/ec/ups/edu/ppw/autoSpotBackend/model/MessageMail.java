package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "SPOT_MESSAGE_MAIL")
public class MessageMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mes_mail_id")
    private String idMessageMail;

    @Column(name = "mes_mail_mailFrom")
    private String mailFrom;

    @Column(name = "mes_mail_nameFrom")
    private String nameFrom;

    @Column(name = "mes_mail_mailDestination")
    private String mailDestination;

    @Column(name = "mes_mail_header")
    private String header;

    @Column(name = "mes_mail_shippingDate")
    private String shippingDate;

    @Column(name = "mes_mail_status")
    @Pattern(regexp = "RD|NR", message = "El status debe ser 'RD', 'NR'")
    private String status;

    public MessageMail() {
    }

    public String getIdMessageMail() {
        return idMessageMail;
    }

    public void setIdMessageMail(String idMessageMail) {
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

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
