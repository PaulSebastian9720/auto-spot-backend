package ec.ups.edu.ppw.autoSpotBackend.api.management;

import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ParkingSpaceDTO;
import ec.ups.edu.ppw.autoSpotBackend.api.dto.others.ReqSpaceDTO;
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

    public void addSpot(ReqSpaceDTO newSpace) throws CustomException {
        if(newSpace.getLocation().split("_").length != 2)
            throw new CustomException(Errors.BAD_REQUEST,"Location format is invalid, the Format is 'SPOT_(letter row or NR)'");

        final String formatLocation = newSpace.getLocation().split("_")[1];

        if (formatLocation.equalsIgnoreCase("NR")) {
            final String newRow = this.newLocation();
            int maxElements = newSpace.getLength();
            maxElements  = (maxElements < 1)? 1 : (maxElements > 4) ? 4 : maxElements;

            for (int i = 0; i < maxElements ; i++) {
                ParkingSpace parkingSpace = new ParkingSpace();
                parkingSpace.setLocation(newRow + "-CL" + (i + 1));
                parkingSpace.setStatus("FR");
                parkingSpaceDAO.insertParkingSpace(parkingSpace);
            }
        } else if (formatLocation.startsWith("RW") && formatLocation.length() == 3 && Character.isLetter(formatLocation.charAt(2))) {
            if(!existsRWPattern(formatLocation)) throw new CustomException(Errors.BAD_REQUEST, "There are not exist that ROW");
            int maxElements = newSpace.getLength();
            int newPostionLocation = (int) this.getNewLocation(formatLocation);

            maxElements  = (maxElements < 1)? 1 : (maxElements > 4) ? 4 : maxElements;
            for (int i = newPostionLocation; i < maxElements + newPostionLocation; i++) {
                ParkingSpace parkingSpace = new ParkingSpace();
                parkingSpace.setLocation(formatLocation + "-CL" + (i + 1));
                parkingSpace.setStatus("FR");
                parkingSpaceDAO.insertParkingSpace(parkingSpace);
            }

        } else {
            throw  new CustomException(Errors.BAD_REQUEST,"Invalid format of the location");
        }

    }

    public ParkingSpace readSpot(int idParkingSpace) throws CustomException {
        if(idParkingSpace <= 0) throw new CustomException(Errors.BAD_REQUEST, "Bad Parking Space ID");
        ParkingSpace parkingSpace = parkingSpaceDAO.readParkingSpace(idParkingSpace);
        if(parkingSpace == null) throw new CustomException(Errors.NOT_FOUND, "Parking Space Not Found");
        return parkingSpace;
    }


    public ParkingSpace getParkingSpace(String location, int idParkingSpace){
        ParkingSpace parkingSpace = new ParkingSpace();
        try {
            if(idParkingSpace > 0 ){
                parkingSpace = this.readSpot(idParkingSpace);

            }else if (location!= null && !location.isEmpty()) {
                parkingSpace =  this.getParkingSpaceByLocation(location);
            }else {
                throw new CustomException(Errors.BAD_REQUEST, "ID parking space or location is required");
            }
        }catch (Exception e){
            throw new CustomException(Errors.NOT_FOUND, "Not found ParkingSpace with this parameters");
        }
        return parkingSpace;
    }

    public ParkingSpace getParkingSpaceByLocation(String location) throws  CustomException{
        if(location == null || location.isEmpty()) throw new CustomException(Errors.BAD_REQUEST, "Parameter Location cannot be null");
        ParkingSpace parkingSpace = this.parkingSpaceDAO.getParkingSpaceByLocation(location);
        if(parkingSpace == null) throw new CustomException(Errors.NOT_FOUND, "Parking Space Not Found");
        return parkingSpace;
    }

    public List<ParkingSpace> getAllSpaces() {
        return this.parkingSpaceDAO.getParkingSpaces();
    }

    public List<ParkingSpaceDTO> getAllParkingSpace() {
        List<ParkingSpaceDTO> listAll = this.parkingSpaceDAO.getParkingSpaces()
                .stream()
                .map(parkingSpace -> ParkingSpaceDTO.fromJsonParkingSpace(parkingSpace))
                .toList();
        return listAll;
    }


    public List<ParkingSpace> getListPerStatus(String status) throws CustomException {
        if (!status.equalsIgnoreCase("FR") && !status.equalsIgnoreCase("IN")) {
            throw new CustomException(Errors.BAD_REQUEST, "Invalid status, only 'FR' or 'IN' are allowed");
        }
        return this.parkingSpaceDAO.getListPerStatus(status);
    }


    public void changeState(int idParkingSpace) throws CustomException{
        ParkingSpace parkingSpace = this.readSpot(idParkingSpace);
        if(parkingSpace == null) throw new CustomException(Errors.NOT_FOUND, "Parking Space Not Found");
        if(parkingSpace.getStatus().toUpperCase().compareTo("BM") == 0
                || parkingSpace.getStatus().toUpperCase().compareTo("BT") == 0)
            throw new CustomException(Errors.UN_AUTHORIZED, "Cant Change State of Busy Parking Space");
        if(parkingSpace.getStatus().toUpperCase().compareTo("FR") == 0) {
            parkingSpace.setStatus("IN");
        } else  if( parkingSpace.getStatus().toUpperCase().compareTo("IN") == 0){
            parkingSpace.setStatus("FR");
        }
        this.parkingSpaceDAO.modifyParkingSpace(parkingSpace);
    }

    public void updateParkingSpace(ParkingSpace parkingSpace){
        this.parkingSpaceDAO.updateParkingSpace(parkingSpace);
    }

    private boolean validatorSpot(ParkingSpace parkingSpace) throws  CustomException {
        if(parkingSpace == null) throw  new CustomException(Errors.BAD_REQUEST, "NO SE ENCONTRO EL SPOT");
        if(parkingSpace.getLocation().compareTo("") == 0) throw new CustomException(Errors.BAD_REQUEST, "NO SE ENCONTRO EL SPOT");
        if(parkingSpace.getStatus().compareTo("") == 0) throw new CustomException(Errors.BAD_REQUEST, "STATE NEEDED");
        return true;
    }

    private String newLocation(){
        final int length = (int) this.getAllSpaces().stream()
                .map(parkingSpace -> parkingSpace.getLocation().split("-")[0])
                .distinct()
                .count();

        return "RW" + (char) ('A' + length);
    }

    private long getNewLocation(String location) throws CustomException {
        return  this.getAllSpaces().stream()
                .map(parkingSpace -> parkingSpace.getLocation().toUpperCase())
                .filter(loc -> loc.startsWith(location.toUpperCase()))
                .count() + 1;

    }

    private boolean existsRWPattern(String location) {
        for (ParkingSpace parkingSpace : this.getAllSpaces()) {
            if (parkingSpace.getLocation().split("-")[0].equalsIgnoreCase(location)) return true;

        }
        return false;
    }


}