package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "SPOT_PERSON")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_id")
	private int id;

    @Column(name = "per_documentID", unique = true)
    private String  documentID;

    @Column(name = "per_name")
    private String name;

    @Column(name = "per_last_name")
    private String lastName;

    @Column(name = "per_mail")
    private String mail;

    @Column(name = "per_role")

    @Pattern(regexp = "A|C|CF", message = "El status debe ser 'A', 'C', 'CF'")
    private String role;

    @Pattern(regexp = "A|I", message = "El status debe ser 'A' O 'I'")
    @Column(name = "per_status")
    private String status;

    @Column(name = "per_birthday")
    private Date birthDay;

    @Column(name = "per_mailS")
    private String mailS;

    @Column(name = "per_location")
    private String location;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_aut_id")
    private List<Automobile> listAutomobiles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_cont_id")
    private List<Contract> listContracts;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_mess_id")
    private List<MessageMail> listMessagesMails;

    public Person() {
        this.listAutomobiles = new ArrayList<>();
        this.listContracts = new ArrayList<>();
        this.listMessagesMails = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getMailS() {
        return mailS;
    }

    public void setMailS(String mailS) {
        this.mailS = mailS;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Automobile> getListAutomobiles() {
        return listAutomobiles;
    }

    public void setListAutomobiles(List<Automobile> listAutomobiles) {
        this.listAutomobiles = listAutomobiles;
    }

    public List<Contract> getListContracts() {
        return listContracts;
    }

    public void setListContracts(List<Contract> listContracts) {
        this.listContracts = listContracts;
    }
    
    public void addAutomobile(Automobile automobile) {
        if(automobile == null) return;
        this.listAutomobiles.add(automobile);

    }

}
