package ec.ups.edu.ppw.autoSpotBackend.api.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;

import java.util.Date;


public class UserDTO {
    private int idPerson;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String mail;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role;
    private String name;
    private String documentID;
    private String lastName;
    private String phone;
    private Date birthDay;
    private String mailS;
    private String location;

    public UserDTO() {}

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static UserDTO fromPersonModel(Person person){
        UserDTO dto = new UserDTO();
        dto.setIdPerson(person.getIdPerson());
        dto.setMail(person.getMailUser().getMail());
        dto.setDocumentID(person.getDocumentID());
        dto.setName(person.getName());
        dto.setLastName(person.getLastName());
        dto.setRole(person.getRole());
        dto.setBirthDay(person.getBirthDay());
        dto.setMailS(person.getMailS());
        dto.setLocation(person.getLocation());
        dto.setPhone(person.getPhone());
        return dto;
    }
}
