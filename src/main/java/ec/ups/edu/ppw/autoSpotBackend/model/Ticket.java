package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "SPOT_TICKET")
public class Ticket  extends DealBase {

    @Column(name = "tick_accessTicket", nullable = false)
    @Size(min = 9, max = 9)
    private String accessTicket;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SPOT_T  TICKET_RATE",
            joinColumns = @JoinColumn(name = "tick_id"),
            inverseJoinColumns = @JoinColumn(name = "rat_id")
    )
    private List<Rate> listRates;

    public Ticket() {
        super();
    }

    public @Size(min = 9, max = 9) String getAccessTicket() {
        return accessTicket;
    }

    public void setAccessTicket(@Size(min = 9, max = 9) String accessTicket) {
        this.accessTicket = accessTicket;
    }

    public List<Rate> getListRates() {
        return listRates;
    }

    public void setListRates(List<Rate> rates) {
        this.listRates = rates;
    }
}
