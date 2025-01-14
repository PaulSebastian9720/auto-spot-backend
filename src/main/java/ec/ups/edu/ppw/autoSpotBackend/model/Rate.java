package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SPOT_RATE")
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rat_id")
	private int idRate;

    @Column(name = "rat_nameRate")
    private String nameRate;

    @Column(name = "rat_prize")
    private double prize;

    @Column(name = "rat_timeUni")
    private String timeUnit;

    @Column(name = "rat_unitRate")
    private int unitRate;

    public int getId() {
        return idRate;
    }

    public void setId(int id) {
        this.idRate = id;
    }

    public String getNameRate() {
        return nameRate;
    }

    public void setNameRate(String nameRate) {
        this.nameRate = nameRate;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(int unitRate) {
        this.unitRate = unitRate;
    }

}
