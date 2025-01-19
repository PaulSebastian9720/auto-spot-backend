package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SPOT_CONTRACT")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cont_id")
	private int idContract;

    @Column(name = "cont_type", nullable = false)
    @Pattern(regexp = "MT|TM", message = "El status debe ser 'MT' o 'TM'")
    private String type;

    @Column(name = "cont_status", nullable = false)
    @Pattern(regexp = "AC|IN|WT|CL", message = "El status debe ser 'AC', 'IN', 'WT', 'CL'")
    private String status;

    @Column(name = "cont_prize")
    private double prize;

    @Column(name = "cont_startDate", nullable = false)
    private Date startDate;

    @Column(name = "cont_endDate")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name ="cont_per_id", nullable = false)
    private Person person;
    
    @OneToOne
    @JoinColumn(name="cont_par_id",nullable = false)
    private ParkingSpace parkingSpace;

    @ManyToMany
    @JoinTable(
            name = "SPOT_CONTRACT_RATE",
            joinColumns = @JoinColumn(name = "cont_id"),
            inverseJoinColumns = @JoinColumn(name = "rat_id")
    )
    private List<Rate> rates;

    @OneToOne
    @JoinColumn(name="cont_aut_id")
    private Automobile automobile;

    public Contract() {
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public @Pattern(regexp = "MT|TM", message = "El status debe ser 'MT' o 'TM'") String getType() {
        return type;
    }

    public void setType(@Pattern(regexp = "M|R", message = "El status debe ser 'M' o 'E'") String type) {
        this.type = type;
    }

    public @Pattern(regexp = "AC|IN|WT|CL", message = "El status debe ser 'AC', 'IN', 'WT', 'CL'") String getStatus() {
        return status;
    }

    public void setStatus(@Pattern(regexp = "AC|IN|WT|CL", message = "El status debe ser 'AC', 'IN', 'WT', 'CL'") String status) {
        this.status = status;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }
}
