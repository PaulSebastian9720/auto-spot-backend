package ec.ups.edu.ppw.autoSpotBackend.dao;

import ec.ups.edu.ppw.autoSpotBackend.model.Schedule;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class ScheduleDAO {

	@PersistenceContext(unitName = "autoSpotBackendPersistenceUnit")
	private EntityManager em;
	
	public void insertSchedule(Schedule schedule) {
		em.persist(schedule);
	}
	
	public Schedule readSchedule(int id) {
		return em.find(Schedule.class, id);
	}
	
	public Schedule modifySchedule(Schedule schedule) {
		return em.merge(schedule);
	}
	
	public void deleteSchedule(int id) {
		Schedule schedule = em.find(Schedule.class, id);
		if (schedule == null) {
	        throw new IllegalArgumentException("Contract with ID " + id + " not found");
	    }
		em.remove(schedule);
	}
	
	public List<Schedule> getSchedules(){
		String jplql = "SELECT s FROM Schedule s";
		Query q = em.createQuery(jplql, Schedule.class);
		List<Schedule> list = q.getResultList();
		return list;
	}
	

}
