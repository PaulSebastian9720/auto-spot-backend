package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class PersonDAO {
	@PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
	private EntityManager em;

	public void insertPerson(Person person) {
		em.persist(person);
	}
	
	public Person readPerson(int id) {
		return em.find(Person.class, id);
	}
	
	public Person modifyPerson(Person person) {
		return em.merge(person);
	}

	public List<Person> getPersons(){
		String jpql = "SELECT p FROM Person p";
		Query q = em.createQuery(jpql, Person.class);
		return q.getResultList();
	}
}
