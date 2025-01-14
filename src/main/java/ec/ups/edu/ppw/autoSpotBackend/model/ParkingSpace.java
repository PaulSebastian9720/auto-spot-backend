package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "SPOT_PARKING_SPACE")
public class ParkingSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "park_spa_id")
	private int idParkingSpace;

    @Column(name = "park_spa_location")
    private String location;

    @Column(name = "park_spa_status")
    @Pattern(regexp = "D|ND|O|I", message = "El status debe ser 'D', 'ND', 'O', 'I'")
    private char status;


    public int getId() {
        return idParkingSpace;
    }

    public void setId(int idParkingSpace) {
        this.idParkingSpace = idParkingSpace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
	
	

}
