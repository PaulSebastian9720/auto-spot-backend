package ec.ups.edu.ppw.autoSpotBackend.business;

import ec.ups.edu.ppw.autoSpotBackend.api.security.JwtTokenProvider;
import ec.ups.edu.ppw.autoSpotBackend.dao.ContractDAO;
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
	private ServiceSendMail sendMail;

	@Inject
	private PersonDAO personDao;

	@Inject
	private ParkingSpaceDAO parkingSpaceDAO;

	@Inject
	private RateDAO rateDao;

	@Inject
	private JwtTokenProvider jwtTokenProvider;

	@Inject
	private ContractDAO contractDAO;


	@PostConstruct
	public void init() {

		this.InsertsPerson();
		this.initMatrizSpace();
		this.insertRates();
		this.initContracts();
		System.out.println("ENVIO DE CORROOOOOOO");
		this.sendMail.sendEmail(
				"paulinio962012@gmail.com",
				"Bienvenido a AutoSpot",
				"Estimado usuario, gracias por registrarse en AutoSpot. Disfrute de nuestras opciones de alquiler de autos."
		);
	}

	private void insertRates() {
		// Primera tarifa
		Rate rate1 = new Rate();
		rate1.setName("Quarter-Hour Rate");
		rate1.setPrize(0.35);
		rate1.setTimeUnit("15_minutes");
		this.rateDao.insertRate(rate1);

		// Segunda tarifa
		Rate rate2 = new Rate();
		rate2.setName("Half-hour Rate");
		rate2.setPrize(0.5);
		rate2.setTimeUnit("30_minutes");
		this.rateDao.insertRate(rate2);

		//Tercera tarifa
		Rate rate3 = new Rate();
		rate3.setName("Hour Rate");
		rate3.setPrize(1.00);
		rate3.setTimeUnit("1_hour");
		this.rateDao.insertRate(rate3);

		//Cuarta tarifa
		Rate rate4 = new Rate();
		rate4.setName("Day Rate");
		rate4.setPrize(4.00);
		rate4.setTimeUnit("1_day");
		this.rateDao.insertRate(rate4);

		// Quinta tarifa
		Rate rate5 = new Rate();
		rate5.setName("Night Rate");
		rate5.setPrize(5.00);
		rate5.setTimeUnit("1_night");
		this.rateDao.insertRate(rate5);

		// Sexta tarifa
		Rate rate6 = new Rate();
		rate6.setName("Week Rate");
		rate6.setPrize(7.00);
		rate6.setTimeUnit("1_week");

		//Septima tarifa
		Rate rate7 = new Rate();
		rate7.setName("Month Rate");
		rate7.setPrize(30.00);
		rate7.setTimeUnit("1_month");
		this.rateDao.insertRate(rate7);

		System.out.println("Rates inserted successfully");
	}


	private void InsertsPerson(){
		System.out.println("Inicio de inserciones");

// Primera persona
		Person person1 = new Person();
		Mail mail1 = new Mail();
		mail1.setMail("paulinio962012@gmail.com");
		person1.setDocumentID("0912345678");
		person1.setName("juan");
		person1.setLastName("salto");
		person1.setMailUser(mail1);
		person1.setRole("A");
		person1.setStatus("A");
		person1.setLocation("Calle Principal 123");


// Segunda persona
		Mail mail2 = new Mail();
		mail2.setMail("karenquito2004@gmail.com");
		Person person2 = new Person();
		person2.setDocumentID("0918765411");
		person2.setName("Carlos Andres");
		person2.setLastName("Perez");
		person2.setMailUser(mail2);
		person2.setRole("C");
		person2.setStatus("I");
		person2.setLocation("Avenida del Sol");
		person2.setPassword("9876543210");
		Mail mail3 = new Mail();
		mail3.setMail("maria.gomez@example.com");
		Person person3 = new Person();
		person3.setDocumentID("0923456789");
		person3.setName("Maria Fernanda");
		person3.setLastName("Gomez");
		person3.setMailUser(mail3);
		person3.setRole("A");
		person3.setStatus("A");
		person3.setLocation("Calle Luna");
		person3.setPassword("1234567890");

		Mail mail4 = new Mail();
		mail4.setMail("paul972012@gmail.com");
		Person person4 = new Person();
		person4.setDocumentID("0956789012");
		person4.setName("Juan Carlos");
		person4.setLastName("Rodriguez");
		person4.setMailUser(mail4);
		person4.setRole("A");
		person4.setStatus("I");
		person4.setLocation("Boulevard Central");
		person4.setPassword("0987654321");

		Mail mail5 = new Mail();
		mail5.setMail("ana.martinez@example.com");
		Person person5 = new Person();
		person5.setDocumentID("0912305678");
		person5.setName("Ana Patricia");
		person5.setLastName("Martinez");
		person5.setMailUser(mail5);
		person5.setRole("C");
		person5.setStatus("A");
		person5.setLocation("Avenida Libertad");
		person5.setPassword("5678901234");

		Mail mail6 = new Mail();
		mail6.setMail("diego.fernandez@example.com");
		Person person6 = new Person();
		person6.setDocumentID("0945678901");
		person6.setName("Diego Alejandro");
		person6.setLastName("Fernandez");
		person6.setMailUser(mail6);
		person6.setRole("C");
		person6.setStatus("I");
		person6.setLocation("Calle del Comercio");
		person6.setPassword("6789012345");

		Mail mail7 = new Mail();
		mail7.setMail("sofia.alarcon@example.com");
		Person person7 = new Person();
		person7.setDocumentID("0934567890");
		person7.setName("Sofia Beatriz");
		person7.setLastName("Alarcon");
		person7.setMailUser(mail7);
		person7.setRole("C");
		person7.setStatus("I");
		person7.setLocation("Avenida del Parque");
		person7.setPassword("8901234567");

		Mail mail8 = new Mail();
		mail8.setMail("pablo.mendoza@example.com");
		Person person8 = new Person();
		person8.setDocumentID("0929876543");
		person8.setName("Pablo Esteban");
		person8.setLastName("Mendoza");
		person8.setMailUser(mail8);
		person8.setRole("C");
		person8.setStatus("A");
		person8.setLocation("Calle 9 de Octubre");
		person8.setPassword("3456789012");

		Mail mail9 = new Mail();
		mail9.setMail("laura.ramirez@example.com");
		Person person9 = new Person();
		person9.setDocumentID("0918765432");
		person9.setName("Laura Gabriela");
		person9.setLastName("Ramirez");
		person9.setMailUser(mail9);
		person9.setRole("C");
		person9.setStatus("I");
		person9.setLocation("Pasaje Colon");
		person9.setPassword("4567890123");

		Mail mail10 = new Mail();
		mail10.setMail("andres.villavicencio@example.com");
		Person person10 = new Person();
		person10.setDocumentID("0954321098");
		person10.setName("Andres Javier");
		person10.setLastName("Villavicencio");
		person10.setMailUser(mail10);
		person10.setRole("C");
		person10.setStatus("A");
		person10.setLocation("Barrio Norte");
		person10.setPassword("2345678901");


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
		message1.setMailFrom(person1.getMailUser().getMail());
		message1.setNameFrom(person1.getName());
		message1.setHeader("CONFIRMACIÓN DE PAGO");
		message1.setMailDestination("soporte@empresa.com");
		message1.setShippingDate(new Date());
		message1.setStatus("RD");
		message1.setPerson(person1);
		person1.addMessageMail(message1);

		MessageMail message2 = new MessageMail();
		message2.setMailFrom(person1.getMailUser().getMail());
		message2.setNameFrom(person1.getName());
		message2.setHeader("SOLICITUD DE SOPORTE");
		message2.setMailDestination("contacto@empresa.com");
		message2.setShippingDate(new Date());
		message2.setStatus("RD");
		message2.setPerson(person1);
		person1.addMessageMail(message2);

// Mensajes enviados por la segunda persona
		MessageMail message3 = new MessageMail();
		message3.setMailFrom(person2.getMailUser().getMail());
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
		this.personDao.insertPerson(person3);
		this.personDao.insertPerson(person4);
		this.personDao.insertPerson(person5);
		this.personDao.insertPerson(person6);
		this.personDao.insertPerson(person7);
		this.personDao.insertPerson(person8);
		this.personDao.insertPerson(person9);
		this.personDao.insertPerson(person10);
		final String tokenPerson1 = this.jwtTokenProvider.createToken(person1.getMailUser().getMail(), person1.getName(), person1.getRole());
		System.out.println("\n \n \n \n \n \n \n \n first person");
		System.out.println(tokenPerson1);
		System.out.println("Is valid this toke:" + this.jwtTokenProvider.validateToken(tokenPerson1));

		final String tokenPerson2 = this.jwtTokenProvider.createToken(person2.getMailUser().getMail(), person2.getName(), person2.getRole());
		System.out.println("\n \n \n \n \n \n \n \n second person");
		System.out.println(tokenPerson2);
		System.out.println("Is valid this toke:" + this.jwtTokenProvider.validateToken(tokenPerson2));
		System.out.println("\n \n \n \n \n \n \n \n");

		System.out.println("Inserciones finalizadas");

	}



	private void initMatrizSpace(){

		for (int i = 0; i < 7 ; i++) {
			char letter = (char) ('A' + i);
			for (int j = 0; j < 7 ; j++) {
				String key = "RW" + letter + "-CL"+(j + 1);
				ParkingSpace parkingSpace = new ParkingSpace();
				parkingSpace.setLocation(key);
				if(j   == 5) {
					parkingSpace.setStatus("IN");
				}else if ( j == 1) {
					parkingSpace.setStatus("BC");
				}else if ( j == 2) {
					parkingSpace.setStatus("BT");
				}else {
					parkingSpace.setStatus("FR");

				}
				this.parkingSpaceDAO.insertParkingSpace(parkingSpace);

			}
		}
	}

	private void initContracts(){
//		Contract contract = new Contract();
//		contract.setStatus("AC");
//		contract.setStartDate(new Date());
//		contract.setAutoRenewal(true);
//		contract.setFinalPrice(25.56);
//		ParkingSpace parkingSpace = new ParkingSpace();
//		parkingSpace.setIdParkingSpace(1);
//		contract.setParkingSpace(parkingSpace);
//		Person person = new Person();
//		person.setIdPerson(1);
//		contract.setPerson(person);
//		Automobile automobile = new Automobile();
//		Rate rate = new Rate();
//		rate.setIdRate(1);
//		contract.setRate(rate);
//		automobile.setIdAutomobile(1);
//		contract.setAutomobile(automobile);
//		contractDAO.insertContract(contract);
	}
}