/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Apr 6, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dmacc.beans.Customer;
import dmacc.beans.Pet;
import dmacc.repository.CustomerRepository;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * Service layer for business logic of program. 
 * CustomerService class with methods to interact with CustomerRepository/DAO
 * SQL query database interactions. 
 */
@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	PetService petService;
	
	public Customer getCustomerById(long customerId) {
		return customerRepo.findById(customerId).get();
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		customerRepo.findAll().forEach(customer -> customers.add(customer));
		return customers;
	}
	
	public void saveCustomerEdit(Customer customer) {
		customerRepo.save(customer);
	}
	
	public void deleteCustomerById(long customerId) {
		customerRepo.deleteById(customerId);
	}
	
	public Customer findCustomerByEmail(String email) {
		return customerRepo.findCustomerByEmail(email);
	}
	
	public Pet getSelectedPet(long customerId) {
		Customer customer = customerRepo.findById(customerId).get();
		return customer.getSelectedPet();
	}
	
	public Pet getSelectedPet(Customer customer) {
		//Customer customer = customerRepo.findById(customerId).get();
		Pet selectedPet = customer.getSelectedPet();
		return selectedPet;
		//return customer.getSelectedPet();
	}
	
	public Pet parse(String selectedPet, Pet pet) {
		//long petId = (long)valueOf(text);
		Long petId = Long.valueOf(selectedPet);
		return this.petService.getPetById(petId);
	}

}
