package ec.ups.edu.ppw.autoSpotBackend.api.dto.others;

import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;

public class ParkingSpaceDTO {
    private int idParkingSpace;
    private String location;
    private String status;
    private int idDealBase;
    private String documentId;
    private String licensePlate;

    public int getIdParkingSpace() {
        return idParkingSpace;
    }

    public void setIdParkingSpace(int idParkingSpace) {
        this.idParkingSpace = idParkingSpace;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdDealBase() {
        return idDealBase;
    }

    public void setIdDealBase(int idDealBase) {
        this.idDealBase = idDealBase;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public static ParkingSpaceDTO fromJsonParkingSpace(ParkingSpace parkingSpace){
        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();

        if(parkingSpace.getDealBase() != null){
//            parkingSpaceDTO.setDocumentId(parkingSpace.getDealBase().getPerson().getDocumentID());
            parkingSpaceDTO.setIdDealBase(parkingSpace.getDealBase().getIdDeal());
            parkingSpaceDTO.setLicensePlate(parkingSpace.getDealBase().getAutomobile().getLicensePlate());
        }
        parkingSpaceDTO.setIdParkingSpace(parkingSpace.getIdParkingSpace());
        parkingSpaceDTO.setLocation(parkingSpace.getLocation());
        parkingSpaceDTO.setStatus(parkingSpace.getStatus());
        return parkingSpaceDTO;
    }
}
