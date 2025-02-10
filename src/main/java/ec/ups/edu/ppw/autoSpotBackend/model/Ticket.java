package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "SPOT_TICKET")
public class Ticket  extends DealBase {

//    @Id
//    @Column(name ="tic_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int idTicket;


    @Column(name = "tic_accesCode", nullable = false)
    @Size(min = 8, max = 8)
    private String accesCode;

    @Column(name = "tic_qrCodeBase")
    private String qrCodeBase;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SPOT_CONTRACT_RATE",
            joinColumns = @JoinColumn(name = "cont_id"),
            inverseJoinColumns = @JoinColumn(name = "rat_id")
    )
    private List<Rate> rates;

    public Ticket() {
        super();
    }

    public @Size(min = 8, max = 8) String getAccesCode() {
        return accesCode;
    }

    public void setAccesCode(@Size(min = 8, max = 8) String accesCode) {
        this.accesCode = accesCode;
    }

    public String getQrCodeBase() {
        return qrCodeBase;
    }

    public void setQrCodeBase(String qrCodeBase) {
        this.qrCodeBase = qrCodeBase;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
