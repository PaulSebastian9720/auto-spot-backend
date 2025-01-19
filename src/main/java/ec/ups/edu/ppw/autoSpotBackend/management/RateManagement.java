package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.RateDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Rate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RateManagement {
    @Inject
    private RateDAO rateDAO;

    public void addRate(Rate rate) throws Exception {
        if(rate == null) throw new Exception("DATOS INVALIDOS DE LA TARIFA");
        if(rate.getPrize() <= 0) throw new Exception("PRECIO INVALIDOS DE LA TARIFA");
        rateDAO.insertRate(rate);
    }

    public void removeRate( int rateID) throws Exception {
        Rate rate = this.getRateById(rateID);
        boolean exist = rateDAO.existContractWithThisRate(rate);
        if(exist) throw new Exception("EXISTE UN CONTRATO CON ESTA TARIFA");
        rateDAO.deleteRate(rateID);
    }

    public Rate getRateById(int rateID) throws Exception {
        if(rateID <= 0) throw new Exception("ID DE LA TARIFA INVALIDO");
         Rate rate = rateDAO.readRate(rateID);
         if(rate == null) throw new Exception("NO EXISTE ESTA TARIFA");
         return rate;
    }

    public void updateRate(Rate rate) throws Exception {
        if(rate == null) throw new Exception("DATOS INVALIDOS DE LA TARIFA");
        if(rate.getPrize() <= 0) throw new Exception("PRECIO INVALIDOS DE LA TARIFA");
        this.getRateById(rate.getIdRate());
        rateDAO.updateRate(rate);
    }

    public List<Rate> getAllRates() throws Exception {
        return this.rateDAO.getRates();
    }

}
