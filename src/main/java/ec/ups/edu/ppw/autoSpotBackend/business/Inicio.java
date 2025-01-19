package ec.ups.edu.ppw.autoSpotBackend.business;

import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.Automobile;
import ec.ups.edu.ppw.autoSpotBackend.model.MessageMail;
import ec.ups.edu.ppw.autoSpotBackend.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.Date;

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
		person.setMail("naspud972012@gmail.es");
		person.setRole("A");
		person.setStatus("A");
		person.setLocation("Av. San Pedro");

		Person person2 = new Person();
		person2.setDocumentID("0303149602");
		person2.setName("Paul Sebastian");
		person2.setLastName("Naspud");
		person2.setMail("naspud972012@gmail.com");
		person2.setRole("A");
		person2.setStatus("A");
		person2.setLocation("Av. San Pedro");

		this.personDao.insertPerson(person2);
		Automobile  automobile = new Automobile();
		automobile.setBrand("TOYOTA");
		automobile.setModel("Auto azul");
		automobile.setLicensePlate("VAXF-3215");
		automobile.setPerson(person);
		person.addAutomobile(automobile);

		Automobile  automobile2 = new Automobile();
		automobile2.setBrand("TOYOTA");
		automobile2.setModel("Auto azul");
		automobile2.setLicensePlate("VAXF-3210");
		automobile2.setPerson(person);

		person.addAutomobile(automobile2);

		Automobile  automobile3 = new Automobile();
		automobile3.setBrand("TOYOTA");
		automobile3.setModel("Auto azul");
		automobile3.setLicensePlate("AXF-3210");
		automobile3.setPerson(person);

		person.addAutomobile(automobile3);


		MessageMail messageMail = new MessageMail();
		messageMail.setMailFrom(person.getMail());
		messageMail.setNameFrom(person.getName());
		messageMail.setHeader("FINALIZACION DE CONTRATO");
		messageMail.setMailDestination("naspud972012@gmial.com");
		messageMail.setShippingDate(new Date());
		messageMail.setStatus("RD");
		messageMail.setPerson(person);

		person.addMessageMail(messageMail);

		personDao.insertPerson(person);

	}
}