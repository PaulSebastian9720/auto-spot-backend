package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SPOT_AUTOMOVILE")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aut_id")
    private int idAutomobile;

    @Column(name = "aut_licenseplate", unique = true)
    private String licensePlate;

    @Column(name = "aut_brand")
    private String brand;

    @Column(name = "aut_model")
    private String model;


    public Automobile() {
    }

    public int getId() {
        return idAutomobile;
    }

    public void setId(int id) {
        this.idAutomobile = id;
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

}
