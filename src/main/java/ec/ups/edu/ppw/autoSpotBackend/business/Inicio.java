package ec.ups.edu.ppw.autoSpotBackend.business;

import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Inicio {

	@Inject
	private PersonDAO personDao;
	
	@PostConstruct
	public void init() {
		System.out.println("Hola mundo EJB");

		Person person = new Person();
		person.setDocumentID("0303149603");
		person.setName("Paul Sebastian");
		person.setLastName("Naspud");
		person.setMail("0301547895");
		person.setRole("A");
		person.setStatus("A");
		person.setLocation("Av. San Pedro");

		Automobile  automobile = new Automobile();
		automobile.setBrand("TOYOTA");
		automobile.setModel("Auto azul");
		automobile.setLicensePlate("VAXF-3215");

		person.addAutomobile(automobile);

		personDao.insertPerson(person);

	}
}