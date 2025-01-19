package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Contract;
import ec.ups.edu.ppw.autoSpotBackend.model.Rate;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class RateDAO {

    @PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
    private EntityManager em;
	
	public void insertRate(Rate rate) {
        em.persist(rate);
    }
	
	public Rate readRate(int idRate) {
		return em.find(Rate.class, idRate);
        
    }

    public Rate updateRate(Rate rate) {
    	Rate r = em.merge(rate);
    	return r;
    }


    public void deleteRate(int id) {
    	Rate rate = em.find(Rate.class, id);
    	em.remove(rate);
    }

    public List<Rate> getRates() {
    	String jpql = "SELECT r FROM Rate r"; 
        Query q = em.createQuery(jpql, Rate.class); 
        List<Rate> list = q.getResultList(); 
        return list;
    }

    public boolean existContractWithThisRate(Rate rate) {
        String jpql = "SELECT c FROM Contract c WHERE c.status = 'AC' AND :rate MEMBER OF c.rates";
        Query q = em.createQuery(jpql, Contract.class);
        q.setParameter("rate", rate);
        List<Contract> list = q.getResultList();
        return !list.isEmpty();
    }

}
