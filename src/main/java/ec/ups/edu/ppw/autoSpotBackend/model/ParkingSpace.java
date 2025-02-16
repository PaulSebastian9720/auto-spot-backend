package ec.ups.edu.ppw.autoSpotBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "SPOT_PARKING_SPACE")
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "park_spa_id")
    private int idParkingSpace;

    @Column(name = "park_spa_location", nullable = false, unique = true)
    private String location;

    @Column(name = "park_spa_status", nullable = false)
    @Pattern(regexp = "FR|BC|BT|IN", message = "The state had be 'FR', 'BM' , 'BT', 'IN'")
    private String status;

    @OneToOne
    @JoinColumn(name = "park_spa_cont", referencedColumnName = "deal_id")
    @JsonBackReference
    private DealBase dealBase;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public @Pattern(regexp = "FR|BC|BT|IN", message = "The state had be 'FR', 'BC' , 'BT', 'IN'") String getStatus() {
        return status;
    }

    public void setStatus(@Pattern(regexp = "FR|BC|BT|IN", message = "The state had be 'FR', 'BC' , 'BT', 'IN'") String status) {
        this.status = status;
    }

    public int getIdParkingSpace() {
        return idParkingSpace;
    }

    public void setIdParkingSpace(int idParkingSpace) {
        this.idParkingSpace = idParkingSpace;
    }

    public DealBase getDealBase() {
        return dealBase;
    }

    public void setDealBase(DealBase dealBase) {
        this.dealBase = dealBase;
    }

    @JsonbProperty("idDealBase")
    public Integer getIdDealBase() {
        return dealBase != null ? dealBase.getIdDeal() : null;
    }
}
