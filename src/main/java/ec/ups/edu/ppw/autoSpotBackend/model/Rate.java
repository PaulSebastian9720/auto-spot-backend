package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "SPOT_RATE")
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rat_id")
	private int idRate;

    @Column(name = "rat_name")
    private String name;

    @Column(name = "rat_prize", nullable = false)
    private double prize;

    @Column(name = "rat_timeUni", nullable = false)
    @Pattern(regexp = "5_minutes|10_minutes|30_minutes|1_hour|1_month",
            message = "El status debe ser '5_minutes' o '10_minutes' o '30_minutes' o '1_hour' o '1_month'")
    private String timeUnit;

    public Rate() {}

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public @Pattern(regexp = "5_minutes|10_minutes|30_minutes|1_hour|1_month",
            message = "El status debe ser '5_minutes' o '10_minutes' o '30_minutes' o '1_hour' o '1_month'") String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(@Pattern(regexp = "5_minutes|10_minutes|30_minutes|1_hour|1_month",
            message = "El status debe ser '5_minutes' o '10_minutes' o '30_minutes' o '1_hour' o '1_month'") String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
