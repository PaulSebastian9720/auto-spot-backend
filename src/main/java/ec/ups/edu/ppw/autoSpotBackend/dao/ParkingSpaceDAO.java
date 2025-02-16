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
		String jpql = "SELECT p FROM ParkingSpace p";
		Query q = em.createQuery(jpql, ParkingSpace.class);
		return q.getResultList();
	}

	public ParkingSpace getParkingSpaceByLocation(String location){
		String jpql = "SELECT p FROM ParkingSpace p WHERE p.location = :location";
        Query q = em.createQuery(jpql, ParkingSpace.class);
        q.setParameter("location", location);
		List<ParkingSpace> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
	}

	public List<ParkingSpace> getListPerStatus(String status){
		String jpql = "SELECT p FROM ParkingSpace p WHERE p.status =:status";
		Query q = em.createQuery(jpql, ParkingSpace.class);
		q.setParameter("status", status);
		return q.getResultList();
	}
}
