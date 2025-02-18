package ec.ups.edu.ppw.autoSpotBackend.api.dto.others;

public class ReqTicketDTO {
    private ReqDealBaseDTO.Person person;
    private ReqDealBaseDTO.Automobile automobile;
    private int [] listRates;
    private ReqDealBaseDTO.ParkingSpace parkingSpace;
    private boolean autoRenewal;

    public ReqTicketDTO() {
    }

    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public ReqDealBaseDTO.Person getPerson() {
        return person;
    }

    public void setPerson(ReqDealBaseDTO.Person person) {
        this.person = person;
    }

    public ReqDealBaseDTO.Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(ReqDealBaseDTO.Automobile automobile) {
        this.automobile = automobile;
    }

    public int[] getListRates() {
        return listRates;
    }

    public void setListRates(int[] listRates) {
        this.listRates = listRates;
    }

    public ReqDealBaseDTO.ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ReqDealBaseDTO.ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public static class Person{
        private int idPerson;
        private String documentID;

        public int getIdPerson() {
            return idPerson;
        }

        public void setIdPerson(int idPerson) {
            this.idPerson = idPerson;
        }

        public String getDocumentID() {
            return documentID;
        }

        public void setDocumentID(String documentID) {
            this.documentID = documentID;
        }
    }

    public  static class Automobile {
        private String licensePlate;
        private int idAutomobile;

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public int getIdAutomobile() {
            return idAutomobile;
        }

        public void setIdAutomobile(int idAutomobile) {
            this.idAutomobile = idAutomobile;
        }
    }

    public static class ParkingSpace {
        private int idParkingSpace;
        private String location;

        public int getIdParkingSpace() {
            return idParkingSpace;
        }

        public void setIdParkingSpace(int idParkingSpace) {
            this.idParkingSpace = idParkingSpace;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

}
