package ec.ups.edu.ppw.autoSpotBackend.api.dto.others;


public class ReqContractDTO {
    private Person person;
    private Automobile automobile;
    private int idRate;
    private ParkingSpace parkingSpace;
    private boolean autoRenewal;

    public ReqContractDTO() {
    }

    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
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