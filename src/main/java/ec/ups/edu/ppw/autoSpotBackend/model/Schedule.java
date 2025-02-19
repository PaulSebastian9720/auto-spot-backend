package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Table(name = "SPOT_SCHEDULE")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_id")
	private int idDay;

    @Column(name ="day_status", nullable = false)
    @Pattern(regexp = "R|E|NW", message = "El status debe ser 'R', 'E' o 'NW'")
    private String status;  

    @Column(name = "day_dayName")
    private String dayName;

    @Column(name = "day_openingTime")
    private Date openingTime;

    @Column(name = "day_closingTime")
    private Date closingTime;

    @Column(name = "day_exceptionDay")
    private Date exceptionDay;

    public Schedule() {
    }

    public int getIdDay() {
		return idDay;
	}

	public void setIdDay(int idDay) {
		this.idDay = idDay;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public String getDayName() {
        return dayName;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExceptionDay() {return exceptionDay;}

    public void setExceptionDay(Date exceptionDay) {this.exceptionDay = exceptionDay;}
}
