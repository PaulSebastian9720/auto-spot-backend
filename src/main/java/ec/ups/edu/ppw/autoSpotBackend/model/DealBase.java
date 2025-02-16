package ec.ups.edu.ppw.autoSpotBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SPOT_DEAL_BASE")
public abstract class DealBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private int idDeal;

    @Column(name = "deal_status", nullable = false)
    @Pattern(regexp = "AC|IN|WT|CL", message = "The status had be 'AC', 'IN', 'WT', 'CL'")
    private String status;

    @Column(name = "deal_finalPrice")
    private double finalPrice;

    @OneToOne
    @JoinColumn(name = "deal_aut_id")
    private Automobile automobile;

    @OneToOne
    @JoinColumn(name = "deal_par_id", nullable = false)
    @JsonManagedReference
    private ParkingSpace parkingSpace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aut_per", nullable = false)
    @JsonBackReference
    private Person person;

    @Column(name = "deal_startDate", nullable = false)
    private Date startDate;

    @Column(name = "deal_endDate")
    private Date endDate;

    public DealBase() {
    }

    public int getIdDeal() {
        return idDeal;
    }

    public void setIdDeal(int idDeal) {
        this.idDeal = idDeal;
    }

    public @Pattern(regexp = "AC|IN|WT|CL", message = "The status had be 'AC', 'IN', 'WT', 'CL'") String getStatus() {
        return status;
    }

    public void setStatus(@Pattern(regexp = "AC|IN|WT|CL", message = "The status had be 'AC', 'IN', 'WT', 'CL'") String status) {
        this.status = status;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Person getPerson() {
        return person;
    }

    @JsonProperty("idPerson")
    public int getIdPerson() {
        return person != null ? person.getIdPerson() : null;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
