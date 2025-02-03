package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SPOT_DEAL_BASE")
public abstract class DealBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContract;

    @Column(name = "deal_status", nullable = false)
    private String status;

    @Column(name = "deal_prize")
    private double prize;

    @OneToOne
    @JoinColumn(name = "deal_aut_id")
    private Automobile automobile;

    @OneToOne
    @JoinColumn(name = "deal_par_id", nullable = false)
    private ParkingSpace parkingSpace;

    @OneToOne
    @JoinColumn(name = "deal_per_id")
    private Person person;

    @Column(name = "deal_startDate", nullable = false)
    private Date startDate;

    @Column(name = "deal_endDate")
    private Date endDate;
}
