package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class AutomobileDAO {
    @PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
	EntityManager em;
	
	public void insertAutomobile(Automobile automobile) {
		em.persist(automobile);
    }

    public Automobile updateAutomobile(Automobile automobile){
        return em.merge(automobile);
    }

    public void deleteAutomobile(int id) {
        Automobile automobile = em.find(Automobile.class, id);
        if (automobile == null) {
            throw new IllegalArgumentException("Contract with ID " + id + " not found");
        }
        em.remove(automobile);
    }

    public Automobile readAutomobile(int id) {
    	return em.find(Automobile.class, id);
    }

    public List<Automobile> getAutomobiles(){
    	String jpql = "SELECT a FROM Automobile a";
        Query q = em.createQuery(jpql, Automobile.class); 
        return q.getResultList(); 
    }

    public Automobile getAutomobileByLicensePlate(String licensePlate) {
        String jpql = "SELECT a FROM Automobile a WHERE a.licensePlate = :licensePlate";
        Query query = em.createQuery(jpql, Automobile.class);
        query.setParameter("licensePlate", licensePlate);
        List<Automobile> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Automobile> getListByIDPerson(int idPerson) {
        String jpql = "SELECT a FROM Automobile a WHERE a.person.id = :idPerson";
        Query query = em.createQuery(jpql, Automobile.class);
        query.setParameter("idPerson", idPerson);
        List<Automobile> automobiles = query.getResultList();
        return automobiles;
    }


}
