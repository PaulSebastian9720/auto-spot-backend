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

    @Column(name = "per_mail", unique = true, nullable = false)
    private String mail;

    @Column(name = "per_role")
    @Pattern(regexp = "A|C|CF", message = "El status debe ser 'A', 'C', 'CF'")
    private String role;

    @Pattern(regexp = "A|I", message = "El status debe ser 'A' O 'I'")
    @Column(name = "per_status")
    private String status;

    @Column(name = "per_birthday")
    private Date birthDay;

    @Column(name = "per_mailS", unique = true)
    private String mailS;

    @Column(name = "per_location")
    private String location;

    @Column(name = "per_password", nullable = false)
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Automobile> listAutomobiles;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Contract> listContracts;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<MessageMail> listMessagesMails;

    public Person() {
        this.listAutomobiles = new ArrayList<>();
        this.listContracts = new ArrayList<>();
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public void setPassword(@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String password) {
        this.password = password;
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
        person.setMail(userDTO.getMail());
        person.setDocumentID(userDTO.getDocumentID());
        person.setName(userDTO.getName());
        person.setLastName(userDTO.getLastName());
        person.setRole(userDTO.getRole());
        person.setBirthDay(userDTO.getBirthDay());
        person.setMailS(userDTO.getMailS());
        person.setLocation(userDTO.getLocation());
        return person;
    }

}
