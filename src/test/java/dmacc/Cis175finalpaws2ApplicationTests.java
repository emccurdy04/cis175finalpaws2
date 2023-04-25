package dmacc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import dmacc.beans.Customer;
import dmacc.beans.Pet;
import dmacc.beans.Volunteer;
import dmacc.repository.CustomerRepository;
import dmacc.repository.PetRepository;
import dmacc.repository.VolunteerRepository;
import dmacc.service.CustomerService;
import dmacc.service.PetService;
import dmacc.service.VolunteerService;


@RunWith(SpringRunner.class)
@SpringBootTest
class Cis175finalpaws2ApplicationTests {

	@Autowired
	CustomerService customerServ;
	@Autowired
	PetService petServ;
	@Autowired
	VolunteerService volServ;
	
	@MockBean
	CustomerRepository customerRepo;
	@MockBean
	PetRepository petRepo;
	@MockBean
	VolunteerRepository volRepo;
	
	
	/*
	 * Tests for Custom repository and service
	 */
	@Test
	public void getAllCustomersTest() {
		when(customerRepo.findAll()).thenReturn(Stream.of(new Customer("sylwia","glod","Main","Des Moines","Iowa","50309","123-456-7890","sylwiaglodc@gmail.com"),new Customer("Piwa","Cloonan","Pastoral","Des Moines","Iowa","50309","456-756-0456","piwabear@gmail.com"),new Customer("Mary","glod","Glenn","Des Moines","Iowa","50309","123-456-7890","Glencoco@gmail.com")).collect(Collectors.toList()));
		assertEquals(3,customerServ.getAllCustomers().size());
	}
	
	@Test
	public void findCustomerByEmailTest() {
		
		String email = "123abcd@gmail.com";
		
		when(customerRepo.findCustomerByEmail(email)).thenReturn(new Customer("Mary","glod","Main","Des Moines","Iowa","50309","123-456-7890","123abcd@gmail.com"));
		
		assertEquals("123abcd@gmail.com", customerServ.findCustomerByEmail(email).getEmail());
	}
	
	/*
	 * Test for Pet repository and service
	 */
	@Test
	public void getAllPetsTest() {
		when(petRepo.findAll()).thenReturn(Stream.of(new Pet("Piwa","dog","whippet mix","female"),new Pet("Fifa","dog","Pitbull","male"),new Pet("Rocko","Ferret","snow","female"),new Pet("Melany","cat","hairless","male")).collect(Collectors.toList()));
		assertEquals(4, petServ.getAllPets().size());
	}
	

	/*
	 * Test for Volunteer repository and service
	 */
	
	@Test
	public void getAllVolunteersTest() {
		when(volRepo.findAll()).thenReturn(Stream.of(new Volunteer("sylwia","glod","Main","Des Moines","Iowa","50309","123-456-7890","sylwiaglodc@gmail.com"),new Volunteer("Piwa","Cloonan","Pastoral","Des Moines","Iowa","50309","456-756-0456","piwabear@gmail.com"),new Volunteer("Mary","glod","Glenn","Des Moines","Iowa","50309","123-456-7890","Glencoco@gmail.com")).collect(Collectors.toList()));
		assertEquals(3, volServ.getAllVolunteers().size());
	}
	
	@Test
	public void getVolunteerByEmailTest() {
		
		String email = "123abc567@gmail.com";
		when(volRepo.findVolunteerByEmail(email)).thenReturn(new Volunteer("sylwia","glod","Main","Des Moines","Iowa","50309","123-456-7890","123abc567@gmail.com"));
		assertEquals(email,volServ.getVolunteerByEmail(email).getEmail());
	}
	
}
