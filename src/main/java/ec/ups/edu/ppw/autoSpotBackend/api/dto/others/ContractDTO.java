package ec.ups.edu.ppw.autoSpotBackend.api.dto.others;

import ec.ups.edu.ppw.autoSpotBackend.model.Rate;

import java.util.List;

public class ContractDTO {
    private int id;
    private int idPerson;
    private int idAutomobile;
    private List<Rate> listRates;

    public ContractDTO() {
    }
}
