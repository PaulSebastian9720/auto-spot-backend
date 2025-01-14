package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.ParkingSpace;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class ParkingSpaceDAO {

	@PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
	private EntityManager em;
	
	public void insertParkingSpace(ParkingSpace parkingSpace) {
		em.persist(parkingSpace);
	}
	
	public ParkingSpace readParkingSpace(int id) {
		return em.find(ParkingSpace.class, id);
	}
	
	public ParkingSpace modifyParkingSpace(ParkingSpace parkingSpace) {
		return em.merge(parkingSpace);
	}
	
	public void deleteParkingSpace(int id) {
		ParkingSpace parkingSpace = em.find(ParkingSpace.class, id);
		if (parkingSpace == null) {
	        throw new IllegalArgumentException("Contract with ID " + id + " not found");
	    }
		em.remove(parkingSpace);
	}
	
	public List<ParkingSpace> getParkingSpaces(){
		String jpql = "SELECT pa FROM ParkingSpace pa";
		Query q = em.createNativeQuery(jpql, ParkingSpace.class);
		return q.getResultList();
	}
}
