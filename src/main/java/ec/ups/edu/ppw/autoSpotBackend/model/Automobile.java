package ec.ups.edu.ppw.autoSpotBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "SPOT_AUTOMOVILE")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aut_id")
    private int idAutomobile;

    @Column(name = "aut_licenseplate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "aut_brand")
    private String brand;

    @Column(name = "aut_model")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aut_per_id", nullable = false)
    @JsonBackReference
    private Person person;

    public Automobile() {
    }

    public int getIdAutomobile() {
        return idAutomobile;
    }

    public void setIdAutomobile(int idAutomobile) {
        this.idAutomobile = idAutomobile;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
