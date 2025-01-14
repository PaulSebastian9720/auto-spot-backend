package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Table(name = "SPOT_CONTRACT")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cont_id")
	private int idContract;

    @Column(name = "cont_type")
    @Pattern(regexp = "M|R", message = "El status debe ser 'M' o 'E'")
    private char type;

    @Column(name = "cont_status")
    @Pattern(regexp = "A|I|W|C", message = "El status debe ser 'A', 'I' W 'C'")
    private char status;

    @Column(name = "cont_prize")
    private double prize;

    @Column(name = "cont_startDate")
    private Date startDate;

    @Column(name = "cont_endDate")
    private Date endDate;
    
    @ManyToOne
    @JoinColumn(name ="cont_per_id")
    private Person person;
    
    @OneToOne
    @JoinColumn(name="cont_par_id")
    private ParkingSpace parkingSpace;
    
    @OneToOne
    @JoinColumn(name="cont_rat_id")
    private Rate rate;
    
    public Contract() {
    }

    public int getId() {
        return idContract;
    }

    public void setId(int idContract) {
        this.idContract = idContract;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
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

    public ParkingSpace addParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

//    public Rate getRate() {
//        return rate;
//    }
//
//    public void setRate(Rate rate) {
//        this.rate = rate;
//    }
}
