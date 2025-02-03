package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SPOT_TICKET")
public class Ticket {

    @Id
    @Column(name ="tic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    @Column(name = "tic_status")
    private String status;

    @Column(name = "tic_prize")
    private double prize;

//    @ManyToMany
//    @JoinColumn(name = "tic_ra")
//    private List<Rate> rates;

    @OneToOne
    @JoinColumn(name = "tic_aut_id")
    private Automobile automobile;

    @OneToOne
    @JoinColumn(name = "tic_par_id")
    private ParkingSpace parkingSpace;

    @OneToOne
    @JoinColumn(name = "tic_per_id")
    private Person person;

    public Ticket() {
    }
}
