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
import dmacc.service.CustomerService;

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
	
	//?? might need to add below for Spring MVC web controller - leave commented out for now
	//@Autowired
	//CustomerRepository customerRepo;
	
	// added below method & ? consider commented alterations for public List<Customer> getAllCustomers()
	// method for use in Spring MVC Web controller
	@GetMapping("/viewAllCustomers") //method to be run when /viewAllCustomers link is called 
	public String viewAllCustomers(Model model) {
		if(customerService.getAllCustomers().isEmpty()) {
			return addCustomer(model);
		}
		model.addAttribute("customer", customerService.getAllCustomers());
		return "results";
	}
	
	//?? not sure if below method needs to be changed as shown in commented lines for use in 
	// SPring MVC Web controller
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
	
	@DeleteMapping("/customer/{customerId")
	// ?? unsure if should use long id vs long customerId in next line - start w/ latter version
	//public void deleteCustomer(@PathVariable("customerId") long id) {
	public void deleteCustomer(@PathVariable("customerId") long customerId) {
		customerService.deleteCustomerById(customerId);
	}
	
	@PostMapping("/customer")
	public void addCustomer(@RequestBody Customer customer) {
		customerService.saveCustomerEdit(customer);
	}
	
	// ?? below two methods are alternatives of above for use in Spring MVC web controller 
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("newCustomer", customer);
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

}
