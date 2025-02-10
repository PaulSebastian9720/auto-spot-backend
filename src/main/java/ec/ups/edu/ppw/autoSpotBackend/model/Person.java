package ec.ups.edu.ppw.autoSpotBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.auth.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "SPOT_PERSON")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_id")
	private int idPerson;

    @Column(name = "per_documentID", unique = true)
    private String  documentID;

    @Column(name = "per_name", nullable = false)
    private String name;
    
    @Column(name = "per_last_name")
    private String lastName;

    @Column(name = "per_role")
    @Pattern(regexp = "A|C|CF", message = "The status must be 'A' -> Administer, 'C' -> Client, ")
    private String role;

    @Pattern(regexp = "A|I", message = "The status must be 'A' -> Active O 'I' -> Inactive")
    @Column(name = "per_status")
    private String status;

    @Column(name = "per_birthday")
    private Date birthDay;

    @Column(name = "per_mailS", unique = true)
    private String mailS;

    @Column(name = "per_location")
    private String location;

    @Column(name = "per_phone")
    private String phone;

    @Column(name = "per_password")
    @Size(min = 8, message = "Password must be at least 8 characters\n")
    @JsonIgnore
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mail_id", nullable = false, updatable = true)
    private Mail mailUser;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Automobile> listAutomobiles;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<DealBase>  listDeal;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<MessageMail> listMessagesMails;

    public Person() {
        this.listAutomobiles = new ArrayList<>();
        this.listDeal = new ArrayList<>();
        this.listMessagesMails = new ArrayList<>();
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int id) {
        this.idPerson = id;
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

    public Mail getMailUser() {
        return mailUser;
    }

    public void setMailUser(Mail mail) {
        this.mailUser = mail;
    }

    public @Pattern(regexp = "A|C|CF", message = "El status debe ser 'A', 'C', 'CF'") String getRole() {
        return role;
    }

    public void setRole(@Pattern(regexp = "A|C|CF", message = "El status debe ser 'A', 'C', 'CF'") String role) {
        this.role = role;
    }

    public @Pattern(regexp = "A|I", message = "El status debe ser 'A' O 'I'") String getStatus() {
        return status;
    }

    public void setStatus(@Pattern(regexp = "A|I", message = "El status debe ser 'A' O 'I'") String status) {
        this.status = status;
    }

    public @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(@Size(min = 10, message = "The phone number must have at least 10 numbers\n") String phone) {
        this.phone = phone;
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

    public List<DealBase> getListDeal() {
        return listDeal;
    }

    public void setListDeal(List<DealBase> listDeal) {
        this.listDeal = listDeal;
    }

    public void addAutomobile(Automobile automobile) {
        if(automobile == null) return;
        this.listAutomobiles.add(automobile);
    }

    public List<MessageMail> getListMessagesMails() {
        return listMessagesMails;
    }

    public void setListMessagesMails(List<MessageMail> listMessagesMails) {
        this.listMessagesMails = listMessagesMails;
    }
    public void addMessageMail(MessageMail messageMail) {
        this.listMessagesMails.add(messageMail);
    }

    public static Person fromUserDTO(UserDTO userDTO) {
        Person person = new Person();
        person.setIdPerson(userDTO.getIdPerson());
        person.setDocumentID(userDTO.getDocumentID());
        person.setName(userDTO.getName());
        person.setLastName(userDTO.getLastName());
        person.setRole(userDTO.getRole());
        person.setBirthDay(userDTO.getBirthDay());
        person.setMailS(userDTO.getMailS());
        person.setLocation(userDTO.getLocation());
        person.setPhone(userDTO.getPhone());
        person.setStatus(userDTO.getStatus());
        return person;
    }

}
