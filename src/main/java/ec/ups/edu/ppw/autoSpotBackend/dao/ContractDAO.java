package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class ContractDAO {

	@PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
	private EntityManager em;
	
	public void insertContract(Contract contract) {
		em.persist(contract);
	}
	
	public Contract readContract(int id) {
		return em.find(Contract.class, id);
	}
	
	public Contract modifyContract(Contract contract) {
		return em.merge(contract);
	}
	
	public void deleteContract(int id) {
		Contract contract = em.find(Contract.class, id);
		if (contract == null) {
	        throw new IllegalArgumentException("Contrato con el ID " + id + " no encontrado");
	    }
		em.remove(contract);
	}
	
	public List<Contract> getContracts() {
		String jpql = "SELECT co FROM CONTRACT co";
		Query q = em.createQuery(jpql, Contract.class);
		return q.getResultList();
	}

}
