package ec.ups.edu.ppw.autoSpotBackend.business;

import ec.ups.edu.ppw.autoSpotBackend.dao.ParkingSpaceDAO;
import ec.ups.edu.ppw.autoSpotBackend.dao.PersonDAO;
import ec.ups.edu.ppw.autoSpotBackend.dao.RateDAO;
import ec.ups.edu.ppw.autoSpotBackend.model.*;
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

	@Inject
	private ParkingSpaceDAO parkingSpaceDAO;

	@Inject
	private RateDAO rateDao;

	
	@PostConstruct
	public void init() {
		this.InsertsPerson();
		this.insertSpot();
		this.insertRates();

	}

	private void insertRates() {
		// Primera tarifa
		Rate rate1 = new Rate();
		rate1.setName("Standard Rate");
		rate1.setPrize(20.50);
		rate1.setTimeUnit("5_minutes");
		this.rateDao.insertRate(rate1);

		// Segunda tarifa
		Rate rate2 = new Rate();
		rate2.setName("Premium Rate");
		rate2.setPrize(35.75);
		rate2.setTimeUnit("30_minutes");
		this.rateDao.insertRate(rate2);

		// Tercera tarifa
		Rate rate3 = new Rate();
		rate3.setName("Night Rate");
		rate3.setPrize(40.00);
		rate3.setTimeUnit("1_hour");
		this.rateDao.insertRate(rate3);

		// Cuarta tarifa
		Rate rate4 = new Rate();
		rate4.setName("Weekend Rate");
		rate4.setPrize(50.25);
		rate4.setTimeUnit("1_month");
		this.rateDao.insertRate(rate4);

		System.out.println("Rates inserted successfully");
	}

	private void insertSpot(){
		ParkingSpace space = new ParkingSpace();
		space.setLocation("RA-1");
		space.setStatus("FR");
		space.setContract(null);


		ParkingSpace space2 = new ParkingSpace();
		space2.setLocation("RA-2");
		space2.setStatus("FR");
		space2.setContract(null);

		ParkingSpace space3 = new ParkingSpace();
		space3.setLocation("RA-3");
		space3.setStatus("FR");
		space3.setContract(null);


		ParkingSpace space4 = new ParkingSpace();
		space4.setLocation("RB-1");
		space4.setStatus("FR");
		space4.setContract(null);

		this.parkingSpaceDAO.insertParkingSpace(space);
		this.parkingSpaceDAO.insertParkingSpace(space2);
		this.parkingSpaceDAO.insertParkingSpace(space3);
		this.parkingSpaceDAO.insertParkingSpace(space4);

	}

	private void InsertsPerson(){
		System.out.println("Inicio de inserciones");

// Primera persona
		Person person1 = new Person();
		person1.setDocumentID("0912345678");
		person1.setName("Maria Fernanda");
		person1.setLastName("Lopez");
		person1.setMail("maria.lopez@example.com");
		person1.setRole("C");
		person1.setStatus("A");
		person1.setLocation("Calle Principal 123");

// Segunda persona
		Person person2 = new Person();
		person2.setDocumentID("0918765432");
		person2.setName("Carlos Andres");
		person2.setLastName("Perez");
		person2.setMail("carlos.perez@example.com");
		person2.setRole("A");
		person2.setStatus("I");
		person2.setLocation("Avenida del Sol");

// Automóviles asociados a la primera persona
		Automobile auto1 = new Automobile();
		auto1.setBrand("NISSAN");
		auto1.setModel("Sentra Blanco");
		auto1.setLicensePlate("ABC-1234");
		auto1.setPerson(person1);
		person1.addAutomobile(auto1);

		Automobile auto2 = new Automobile();
		auto2.setBrand("CHEVROLET");
		auto2.setModel("Spark Azul");
		auto2.setLicensePlate("XYZ-5678");
		auto2.setPerson(person1);
		person1.addAutomobile(auto2);

// Automóviles asociados a la segunda persona
		Automobile auto3 = new Automobile();
		auto3.setBrand("HONDA");
		auto3.setModel("Civic Rojo");
		auto3.setLicensePlate("LMN-3456");
		auto3.setPerson(person2);
		person2.addAutomobile(auto3);

		Automobile auto4 = new Automobile();
		auto4.setBrand("FORD");
		auto4.setModel("Fiesta Gris");
		auto4.setLicensePlate("JKL-7890");
		auto4.setPerson(person2);
		person2.addAutomobile(auto4);

// Mensajes enviados por la primera persona
		MessageMail message1 = new MessageMail();
		message1.setMailFrom(person1.getMail());
		message1.setNameFrom(person1.getName());
		message1.setHeader("CONFIRMACIÓN DE PAGO");
		message1.setMailDestination("soporte@empresa.com");
		message1.setShippingDate(new Date());
		message1.setStatus("RD");
		message1.setPerson(person1);
		person1.addMessageMail(message1);

		MessageMail message2 = new MessageMail();
		message2.setMailFrom(person1.getMail());
		message2.setNameFrom(person1.getName());
		message2.setHeader("SOLICITUD DE SOPORTE");
		message2.setMailDestination("contacto@empresa.com");
		message2.setShippingDate(new Date());
		message2.setStatus("RD");
		message2.setPerson(person1);
		person1.addMessageMail(message2);

// Mensajes enviados por la segunda persona
		MessageMail message3 = new MessageMail();
		message3.setMailFrom(person2.getMail());
		message3.setNameFrom(person2.getName());
		message3.setHeader("CONSULTA SOBRE CONTRATO");
		message3.setMailDestination("info@empresa.com");
		message3.setShippingDate(new Date());
		message3.setStatus("NR");
		message3.setPerson(person2);
		person2.addMessageMail(message3);

// Inserción de personas
		this.personDao.insertPerson(person1);
		this.personDao.insertPerson(person2);

		System.out.println("Inserciones finalizadas");

	}
}