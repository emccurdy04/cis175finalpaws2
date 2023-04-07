/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 30, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dmacc.repository.PetRepository;
import dmacc.service.PetService;
import dmacc.beans.Pet;

import dmacc.repository.CustomerRepository;
import dmacc.beans.Customer;
/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * 
 * Spring MVC @Controller annotation tells Spring Boot to use this component
 * to handle web-based requests
 * 
 * ?? This would be used if we will be creating a part of program for
 * ?? employees only to do database viewing/entry/editing/deletion of pets via 
 * ?? webpages.
 * ?? Not sure but if so then do we have to also create separate webcontrollers, 
 * ?? repos & related web pages for each entity - Pet, Volunteer & Customer?
 * ?? I think we would at least need to create a part of program like this with
 * ?? related webpages for the Customer to enter their data?
 */
@Controller
public class WebController {
	@Autowired
	PetRepository repo;
	@Autowired
	CustomerRepository custRepo;
	@Autowired
	PetService petService;

	
	/**
	 * Method to direct to home/index page
	 * Generic web directing to each of the html pages not working
	 * Getting 'whitelabel Error page'
	 * have to go directly to localhost:8080 webpage to get home/index page
	 * to display
	 * Fixed 'whitelabel Error page' issues by putting .html on end of path directions
	 */
	//@RequestMapping("/")
	//@RequestMapping({"/","/index"})
	//@GetMapping({"/","/index"})
	@GetMapping({"/","/index.html"})
	public String index(){
		return "index";
	}
	
	/**
	 * Generic web directing to each of the html pages not working
	 */
	//@RequestMapping("/about")
	@RequestMapping("/about.html")
	public String about() {
		return "about";
	}
	
	//@RequestMapping("/available-animals")
	@RequestMapping("/available-animals.html")
	//Eclipse didn't like hyphenated method name version
	//public String available-animals(){
	public String availableAnimals(){
		return "available-animals";
	}
	
	
	//@GetMapping("/redirect")
	//public String redirect() {
		//return "redirect:PageBeingDirectedTo";
	//}
	//?? w/ above method for redirects from landing page?? then using below method to direct
	// to specific page?? & adding attribute tags to webpage 
	// see https://www.tutorialspoint.com/springmvc/springmvc_page_redirection.htm
	// ??put on webpage a <form method="GET" action="/index.redirect"> 
	// ??<input type="submit" value="Redirect Page" > 
	@GetMapping("/available-animals.html")
	public String availableAnimalsPage(Model model) {
	///public String availableAnimalsPage() {
		if (petService.getAllPets().isEmpty()) {
			return "no pets found in DB";
			//return "error";
		}
		model.addAttribute("pets", petService.getAllPets());
		return "available-animals";
	}
	
	//Commenting these Customer related methods out in WebController & moving to CustomerWebController
//	@GetMapping("/consult-request")
//	public String addNewCustomer(Model model) {
//		Customer customer = new Customer();
//		model.addAttribute("newCustomer", customer);
//		return "input";
//		//return "input.html";
//	}
//	
//	@PostMapping("/consult-request")
//	public String addNewCustomer(@ModelAttribute Customer customer, Model model) {
//		custRepo.save(customer);
//		return "success";
//	}

	//??Comment this Volunteer related method out in WebController & move to VolunteerWebController
	// ?? or leave just the basic navigation to the page here??
	//@RequestMapping("/volunteer")
	@RequestMapping("/volunteer.html")
	public String volunteerPage() {
		return "volunteer";
	}
	
	//@RequestMapping("/contact")
	@RequestMapping("/contact.html")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/success.html")
	public String success() {
		return "success";
	}
	
	/**
	 * Most tutorials I reviewed showed a separate package in src/main/java for exceptions
	 * ?? should we create as part of our testing portion of sprint along w/ the unit
	 * ?? testing ??
	 */
	//@ExceptionHandler //??use this annotation w/ below method call rather than @RequestMapping??
	//public ResponseEntity<String> handle(IOException ex) {
	@RequestMapping("/error.html")
	public String error() {
		return "error";
	}
	
	/**
	 * Method to direct to webpage to view all Pet entity/objects in PAWS DB pets table
	 * - use 2nd version if want both home/index or viewAll paths to go to this webpage
	 */
	// version in lab w/o / in front of viewAll will see if helps white label error page issues
	@GetMapping("viewAll") 
	//@GetMapping("/viewAll")
	//@GetMapping({"/", "/viewAll"})
	public String viewAllPets(Model model) {
//		if(repo.findAll().isEmpty()) {
//			//return addNewPet(model);
//			return "no pets found in DB";
//		}
//		
		model.addAttribute("pets", repo.findAll());
		return "results";
		//return "results.html";
	}
	
		
	@GetMapping("/addPet")
	//@GetMapping("/inputPet")
	public String inputPet(Model model) {
		Pet pet = new Pet();
		model.addAttribute("newPet", pet);
		return "input";
	}
	
	@PostMapping("/updatePet")
	//@PostMapping("/inputPet")
	public String addNewPet(@ModelAttribute("pet") Pet pet, Model model) {
		repo.save(pet);
		//return viewAllPets(model);
		return viewAllPets(model);
	}
	
//	@GetMapping("/addPet")
//	public String addPet(Model model) {
//		Pet pet = new Pet();
//		model.addAttribute("newPet", pet);
//		return "input";
//	}
	
	//@PostMapping("/update/{petId}")
	@PostMapping("/addPet")
	public String addPet(@ModelAttribute("pet")Pet pet, Model model) {
		petService.savePetEdit(pet);
		return viewAllPets(model);
	}
	
	/**
	 * ???not sure which version of 1st line of method to use long petId vs long id
	 * trial 1st w/ long petId version 
	 */
	@GetMapping("/edit/{petId}")
	//@GetMapping("/edit/{id}")
	public String showUpdatePet(@PathVariable("petId") long petId, Model model) {
	//public String showUpdatePet(@PathVariable("petId") long id, Model model) {
		Pet pet = repo.findById(petId).orElse(null);
		//Pet pet = repo.findById(id).orElse(null);
		model.addAttribute("newPet", pet);
		return "input";
		//return "input.html";
	}
	
	/**
	 * ??not sure whether best to use petId vs id
	 */
	@PostMapping("/update/{petId}")
	//@PostMapping("/update/{id}")
	public String editPet(Pet pet, Model model) {
		repo.save(pet);
		return viewAllPets(model);
	}
	
	
	/**
	 * ??not sure whether best to use petId vs id
	 */
	@GetMapping("/delete/{petId}")
	//@GetMapping("/delete/{id}")
	public String deletePet(@PathVariable("petId") long petId, Model model) {
	//public String deletePet(@PathVariable("petId") long id, Model model) {
		Pet pet = repo.findById(petId).orElse(null);
		//Pet pet = repo.findById(id).orElse(null);
		repo.delete(pet);
		return viewAllPets(model);
	}
	
	// WebController methods for searching pet by specified field value
	//@GetMapping("pet/petType/{petType}")
	//public ResponseEntity<List<Pet>> getPetByPetType(@PathVariable(name="petType") String petType){
	//log.infor("Getting list of Pet(s) of petType= {} from the DB.", petType);
	//List<Pet> pets = petService.getPetByType(petType);
	//return new ResponseEntity<>(pets, HttpStatus.OK);
	//}

	@GetMapping("/{petType}") // ?? which @GetMapping version?
	//@GetMapping("pet/petType/{petType}")
	//@GetMapping("/petType/{petType}")
	//@GetMapping("/pet/{petType}")
	//public List<Pet> getPetByType(String petType) {
	//public String getPetByType(String petType) {
	//public String getPetByType(Model model) {
	public String getPetByType(@PathVariable("petType") String petType, Model model) {
		if(petService.getPetByType(petType).isEmpty()) {
			return viewAllPets(model);
		}
		//List<Pet> selectedPets = petService.getPetByType(petType);
		//return displayPets;
		//return results;
		model.addAttribute("selectedPets", petService.getPetByType(petType));
		return "petList";
	}

}
