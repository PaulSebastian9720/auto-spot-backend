package ec.ups.edu.ppw.autoSpotBackend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "SPOT_CONTRACT")
public class Contract extends DealBase {

    @Column(name = "cont_autoRenewal")
    private boolean autoRenewal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cont_rate", nullable = false)
    private Rate rate;

    public Contract() {
        super();
    }

    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }
}