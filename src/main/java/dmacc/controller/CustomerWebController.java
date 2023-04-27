/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Apr 6, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dmacc.beans.Customer;
import dmacc.beans.Pet;
import dmacc.service.CustomerService;
import dmacc.service.PetService;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * 
 * Spring MVC @Controller annotation tells Spring Boot to use this component
 * to handle web-based requests
 */
@Controller
//@RequestMapping(path = "/cust")	//?? unsure if need this line - leave commented out for now
public class CustomerWebController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PetService petService;
	
	//?? might need to add below for Spring MVC web controller - leave commented out for now
	//@Autowired
	//CustomerRepository customerRepo;
	
	// below method stopped working - was routing to results.html page
	@GetMapping("/viewAllCustomers") //method to be run when /viewAllCustomers link is called 
	public String viewAllCustomers(Model model) {
//		if(customerService.getAllCustomers().isEmpty()) {
//			//return addCustomer(model);
//			return "no customers found in DB";
//		}
		// see if change lining line 55 to version on 56 caused issues displaying results page
		//model.addAttribute("customer", customerService.getAllCustomers()); 
		model.addAttribute("customers", customerService.getAllCustomers());
		return "results";
	}
	
	//?? not sure if below method needs to be changed as shown in commented lines for use in 
	// Spring MVC Web controller
	//@GetMapping("/viewAllCustomers")
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
	//public String getAllCustomers(){
			//List<Customer> customers = customerService.getAllCustomers()
			// model.addAttribute("customers", customerService.getAllCustomers());
			// ??or model.addAttribute("customers", customerRepo.findAll());
			// ??if do above line code version would need to add Autowired CustomerRepository to this class
			// return "results";
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/customer/{customerId}")
	// ?? unsure if should use long id vs long customerId in next line - start w/ latter version
	//public Customer getCustomer(@PathVariable("customerId") long id) {
	public Customer getCustomer(@PathVariable("customerId") long customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	//?? not sure if above Customer getCustomer(@PathVariable("customerId") long customerId) method
	// needs changed to below method for use when editing customer in Spring MVC Web controller
	// or if below method just needs to be added to the CustomerWebController to retrieve data from
	// webpage for editing - if customer w/ specified id is not found then create null object to fill
	// with data pulled from webpage
	//@GetMapping("/edit/{customerId}")
	@GetMapping("/editCustomer/{customerId}")
	public String editCustomer(@PathVariable("customerId") long customerId, Model model) {
		Customer customer = customerService.getCustomerById(customerId);
		// ??would need to change above line to below if can't create method in CustomerService to address
		// creation of empty Customer object to put in data from web page for new Customer if 
		// customerId that was searched for is not found
		//??Customer customer = customerRepo.findById(customerId).orElse(null);
		model.addAttribute("newCustomer", customer);
		model.addAttribute("selectablePets", petService.getAllPets());
		return "input";
	}
	
	//@PostMapping("/update/{customerId}")
	@PostMapping("/updateCustomer/{customerId}")
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
	//public String addVolunteer(@ModelAttribute Customer customer, Model model) {
		//customerRepo.save(customer); // ?? does below replace this line??
		customerService.saveCustomerEdit(customer);
		return viewAllCustomers(model);
	}
	
	@DeleteMapping("/customer/{customerId}")
	// ?? unsure if should use long id vs long customerId in next line - start w/ latter version
	//public void deleteCustomer(@PathVariable("customerId") long id) {
	public void deleteCustomer(@PathVariable("customerId") long customerId) {
		customerService.deleteCustomerById(customerId);
	}
	
	//?? re: above @DeleteMapping version of deleteCustomer method vs below @GetMapping version
	// that calls it - if used committed out version w/o call to above method line of code will 
	// need to add @Autowired CustomerRepository customerRepo creation
	//@GetMapping("/delete/{customerId}")
	@GetMapping("/deleteCustomer/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") long customerId, Model model) {
		//Customer customer = customerRepo.findById(customerId).orElse(null);
		//customerRepo.delete(customer);
		customerService.deleteCustomerById(customerId);
		return viewAllCustomers(model);
	}	
	
	@PostMapping("/customer")
	public void addCustomer(@RequestBody Customer customer) {
		customerService.saveCustomerEdit(customer);
	}
	

	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("newCustomer", customer);
		//adding this & addAttribute lines below just caused Pets form to also populate, but not drop down
		//Pet pet = new Pet(); 
		//model.addAttribute("newPet", pet);
		model.addAttribute("selectablePets", petService.getAllPets());
		return "input";
	}
	
	// ?? added below ??alternative of above public void addCustomer(@RequestBody Customer customer)
	// ?? for use in Spring MVC web controller 
	@PostMapping("/addCustomer")
	public String addCustomer(@ModelAttribute("customer") Customer customer, Model model) {
	//public String addVolunteer(@ModelAttribute Customer customer, Model model) {
		//customerRepo.save(customer); // ?? does below replace this line??
		customerService.saveCustomerEdit(customer);
		return viewAllCustomers(model);
	}
	
	@PutMapping("/customer")
	public void editCustomer(@RequestBody Customer customer) {
		customerService.saveCustomerEdit(customer);
	}
	
	//?? in order to get drop down list of available pets to attach to this customer object
	// may need to switch @GetMapping("/addCustomer") - addCustomer(Model model) method to this
	//?? have way for this to be called if selectedPet field is empty?
	@GetMapping("/selectedPet")
	public String getSelectPet(Model model) {
		Customer customer = new Customer();
		//List<Pet> selectablePets = petService.getAllPets()
		model.addAttribute("customer", customer);
		model.addAttribute("selectablePets", petService.getAllPets());
		return "input";
	}
	
	// ?? if use above then should also change the @PostMapping("/addCustomer") addCustomer()
	// method to below
	@PostMapping("/selectedPet")
	public String postSelectedPet(@ModelAttribute("customer") Customer customer, Model model) {
		//the customer.selectedPet field should contain selected pet object 
		//?? if need below to add/save selectedPet to customer object in db table
		customerService.saveCustomerEdit(customer);
		return viewAllCustomers(model);
	}
	
	@GetMapping("/selectablePets")
	public String getSelectablePets(Model model) {
		//Customer customer = new Customer();
		//List<Pet> selectablePets = petService.g
		//model.addAttribute("customer", customer);
		model.addAttribute("selectablePets", petService.getAllPets());
		return "input";
	}
	
	// moved these 2 methods from WebController 
	// methods for dealing with - available-animals page form to POST newCustomer object
	@GetMapping("/consult-request")
	public String addNewCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("newCustomer", customer);
		//return "input";
		return "customer";
	}
	
	
	@PostMapping("/consult-request")	//??trial of changing PostMapping so not same as GetMapping above
	//@PostMapping("/consult-request")
	public String addNewCustomer(@ModelAttribute Customer customer, Model model) {
		//changed line below to next line that calls Service layer
		//custRepo.save(customer);
		customerService.saveCustomerEdit(customer);
		return "success";	//?? if this version of telling it to go to 'success' page after saves doesn't work	
		//return "redirect:/success"; //??change to this if after saves want to redirect to success page??
	}

}
