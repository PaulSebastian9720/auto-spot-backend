package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.exception.CustomException;
import ec.ups.edu.ppw.autoSpotBackend.dao.ParkingSpaceDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import ec.ups.edu.ppw.autoSpotBackend.util.consts.Errors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class SpaceManagement {
    @Inject
    private ParkingSpaceDAO parkingSpaceDAO;

    public void addSpot(ParkingSpace parkingSpace) throws CustomException {
        this.validatorSpot(parkingSpace);
        ParkingSpace parkingSpaceExist = this.parkingSpaceDAO.getParkingSpaceByLocation(parkingSpace.getLocation());
        if(parkingSpaceExist != null) throw new CustomException(Errors.UNAUTHORIZED, "Parking Space Already Exists");
        parkingSpace.setLocation(this.getNewLocation(parkingSpace.getLocation()));
        parkingSpace.setStatus("FR");
        parkingSpace.setContract(null);
        parkingSpaceDAO.insertParkingSpace(parkingSpace);
    }

    public ParkingSpace readSpot(int idParkingSpace) throws CustomException {
        if(idParkingSpace <= 0) throw new CustomException(Errors.BAD_REQUEST, "Bad Parking Space ID");
        ParkingSpace parkingSpace = parkingSpaceDAO.readParkingSpace(idParkingSpace);
        if(parkingSpace == null) throw new CustomException(Errors.NOT_FOUND, "Parking Space Not Found");
        return parkingSpace;
    }

    public ParkingSpace redSpotByPlace(String idPlace) throws CustomException {
        if(idPlace.compareTo(null) == 0) throw new CustomException(Errors.BAD_REQUEST, "idPlace not valid")      ;
        return parkingSpaceDAO.getParkingSpaceByLocation(idPlace);
    }

    public void updateSpot(ParkingSpace parkingSpaceUpdate) throws CustomException {
        this.validatorSpot(parkingSpaceUpdate);
        ParkingSpace parkingSpace = this.readSpot(parkingSpaceUpdate.getIdParkingSpace());
        if(parkingSpace == null) throw new CustomException(Errors.BAD_REQUEST, "Parking Space Not Found");
        if(parkingSpace.getLocation().toUpperCase().compareTo(parkingSpaceUpdate.getLocation().toUpperCase()) != 0) throw new CustomException(Errors.UNAUTHORIZED, "Cannot Update Location's Parking Space");
        this.parkingSpaceDAO.modifyParkingSpace(parkingSpace);
    }

    public List<ParkingSpace> getAllSpaces() {
        return this.parkingSpaceDAO.getParkingSpaces();
    }

    public void changeState(int idParkingSpace) throws CustomException{
        ParkingSpace parkingSpace = this.readSpot(idParkingSpace);
        if(parkingSpace == null) throw new CustomException(Errors.NOT_FOUND, "Parking Space Not Found");
        if(parkingSpace.getStatus().toUpperCase().compareTo("BM") == 0
                || parkingSpace.getStatus().toUpperCase().compareTo("BT") == 0)
            throw new CustomException(Errors.UNAUTHORIZED, "Cant Change State of Busy Parking Space");
        if(parkingSpace.getStatus().toUpperCase().compareTo("FR") == 0) {
            parkingSpace.setStatus("IN");
        } else  if( parkingSpace.getStatus().toUpperCase().compareTo("IN") == 0){
            parkingSpace.setStatus("FR");
        }
        this.parkingSpaceDAO.modifyParkingSpace(parkingSpace);
    }

    private boolean validatorSpot(ParkingSpace parkingSpace) throws  CustomException {
        if(parkingSpace == null) throw  new CustomException(Errors.BAD_REQUEST, "NO SE ENCONTRO EL SPOT");
        if(parkingSpace.getLocation().compareTo("") == 0) throw new CustomException(Errors.BAD_REQUEST, "NO SE ENCONTRO EL SPOT");
        if(parkingSpace.getStatus().compareTo("") == 0) throw new CustomException(Errors.BAD_REQUEST, "STATE NEEDED");
        return true;
    }

    private String getNewLocation(String location) throws CustomException {
        List<ParkingSpace> listParkingSpace = this.getAllSpaces();

        long count = listParkingSpace.stream()
                .map(parkingSpace -> parkingSpace.getLocation().toUpperCase())
                .filter(loc -> loc.startsWith(location.toUpperCase()))
                .count();

        return location + "-" + (count + 1);
    }


}