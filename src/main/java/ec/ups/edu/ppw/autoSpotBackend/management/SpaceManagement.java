package ec.ups.edu.ppw.autoSpotBackend.management;

import ec.ups.edu.ppw.autoSpotBackend.dao.ParkingSpaceDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class SpaceManagement {
    @Inject
    private ParkingSpaceDAO parkingSpaceDAO;

    public void addSpot(ParkingSpace parkingSpace) throws Exception{
        this.validatorSpot(parkingSpace);
        ParkingSpace parkingSpaceExist = this.parkingSpaceDAO.getParkingSpaceByLocation(parkingSpace.getLocation());
        if(parkingSpaceExist != null) throw new Exception("YA EXISTE UNA SPOT CON ESA LOCALIZACION");
        parkingSpace.setLocation(this.getNewLocation(parkingSpace.getLocation()));
        parkingSpace.setStatus("FR");
        parkingSpace.setContract(null);
        parkingSpaceDAO.insertParkingSpace(parkingSpace);
    }

    public ParkingSpace readSpot(int idParkingSpace) throws Exception {
        if(idParkingSpace <= 0) throw new Exception("CODIGO NO VALIDO DE EL SPOT");
        ParkingSpace parkingSpace = parkingSpaceDAO.readParkingSpace(idParkingSpace);
        if(parkingSpace == null) throw new Exception("NO SE ENCONTRO EL SPOT CON EL ID: " + idParkingSpace);
        return parkingSpace;
    }

    public ParkingSpace redSpotByPlace(String idPlace) throws Exception{
        if(idPlace.compareTo(null) == 0) throw new Exception("LOCALIZACION DENTRO DE EL ESPOT INVALIDA");       ;
        return parkingSpaceDAO.getParkingSpaceByLocation(idPlace);
    }

    public void updateSpot(ParkingSpace parkingSpaceUpdate) throws Exception {
        this.validatorSpot(parkingSpaceUpdate);
        ParkingSpace parkingSpace = this.readSpot(parkingSpaceUpdate.getIdParkingSpace());
        if(parkingSpace == null) throw new Exception("NO EXISTE ESTE SPOT");
        if(parkingSpace.getLocation().toUpperCase().compareTo(parkingSpaceUpdate.getLocation().toUpperCase()) != 0) throw new Exception("NO SE PUEDE CAMBIAR  LA LOCALIZACION DE EL ESPACIO");
        this.parkingSpaceDAO.modifyParkingSpace(parkingSpace);
    }

    public List<ParkingSpace> getAllSpaces() {
        return this.parkingSpaceDAO.getParkingSpaces();
    }

    public void changeState(int idParkingSpace) throws Exception{
        ParkingSpace parkingSpace = this.readSpot(idParkingSpace);
        if(parkingSpace == null) throw new Exception("NO SE ENCONTRO EL SPOT");
        if(parkingSpace.getStatus().toUpperCase().compareTo("BM") == 0
                || parkingSpace.getStatus().toUpperCase().compareTo("BT") == 0)
            throw new Exception("NO SE ENCONTRO PUEDE CAMBIAR EL ESTADO DE SPOT OCUPADOS");
        if(parkingSpace.getStatus().toUpperCase().compareTo("FR") == 0) {
            parkingSpace.setStatus("IN");
        } else  if( parkingSpace.getStatus().toUpperCase().compareTo("IN") == 0){
            parkingSpace.setStatus("FR");
        }
        this.parkingSpaceDAO.modifyParkingSpace(parkingSpace);
    }

    private boolean validatorSpot(ParkingSpace parkingSpace) throws  Exception {
        if(parkingSpace == null) throw  new Exception("NO SE ENCONTRO EL ESPACIO");
        if(parkingSpace.getLocation().compareTo("") == 0) throw new Exception("SE NESCECITA LA LOCALIZACION DE EL ESPACIO");
        if(parkingSpace.getStatus().compareTo("") == 0) throw new Exception("SE NESCECITA EL ESTADO DE EL ESPACIO");
        return true;
    }

    private String getNewLocation(String location) throws Exception {
        List<ParkingSpace> listParkingSpace = this.getAllSpaces();

        long count = listParkingSpace.stream()
                .map(parkingSpace -> parkingSpace.getLocation().toUpperCase())
                .filter(loc -> loc.startsWith(location.toUpperCase()))
                .count();

        return location + "-" + (count + 1);
    }


}