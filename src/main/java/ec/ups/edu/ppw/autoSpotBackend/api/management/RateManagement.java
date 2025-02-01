package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.RateDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Rate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RateManagement {
    @Inject
    private RateDAO rateDAO;


    public Rate getRateById(int rateID) throws Exception {
        if(rateID <= 0) throw new Exception("ID DE LA TARIFA INVALIDO");
         Rate rate = rateDAO.readRate(rateID);
         if(rate == null) throw new Exception("NO EXISTE ESTA TARIFA");
         return rate;
    }

    public void updateRate(Rate rate) throws Exception {
        if(rate == null) throw new Exception("DATOS INVALIDOS DE LA TARIFA");
        if(rate.getPrize() <= 0) throw new Exception("PRECIO INVALIDOS DE LA TARIFA");
        Rate existingRate = this.getRateById(rate.getIdRate());
        if (!Objects.equals(existingRate.getTimeUnit(), rate.getTimeUnit())) throw new Exception("NO SE PUEDE MODIFICAR EL TIEMPO UNITARIO DE LA TARIFA");
        rateDAO.updateRate(rate);
    }

    public List<Rate> getAllRates() throws Exception {
        return this.rateDAO.getRates();
    }

}
