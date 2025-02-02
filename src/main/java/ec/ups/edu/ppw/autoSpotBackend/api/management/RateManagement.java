package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.RateDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Rate;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RateManagement {
    @Inject
    private RateDAO rateDAO;


    public Rate getRateById(int rateID) throws CustomException {
        if(rateID <= 0) throw new CustomException(Errors.BAD_REQUEST,"Id is out of range and invalid");
         Rate rate = rateDAO.readRate(rateID);
         if(rate == null) throw new CustomException(Errors.BAD_REQUEST,"There is no rate with id");
         return rate;
    }

    public void updateRate(Rate rate) throws CustomException {
        if(rate == null) throw new CustomException(Errors.BAD_REQUEST,"Rate cannot be null");
        Rate existingRate = this.getRateById(rate.getIdRate());
        if (!Objects.equals(existingRate.getTimeUnit(), rate.getTimeUnit())) throw new CustomException(Errors.BAD_REQUEST,"Cannot update the time unit of this rate");
        if(rate.getPrize() <= 0) throw new CustomException(Errors.BAD_REQUEST,"The prize cannot be negative");
        rateDAO.updateRate(rate);
    }

    public List<Rate> getAllRates() {
        return this.rateDAO.getRates();
    }

}
