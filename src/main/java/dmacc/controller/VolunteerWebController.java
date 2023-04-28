/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Apr 5, 2023
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

import dmacc.beans.Volunteer;
import dmacc.service.PetService;
import dmacc.service.VolunteerService;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * 
 * Spring MVC @Controller annotation tells Spring Boot to use this component
 * to handle web-based requests
 */
@Controller
//@RequestMapping(path = "/vol")	//?? unsure if need this line - leave commented out for now
public class VolunteerWebController {
	
	@Autowired
	VolunteerService volunteerService;
	
	//?? might need to add below for Spring MVC web controller - leave commented out for now
	//@Autowired
	//VolunteerRepository volunteerRepo;
	
	@Autowired
	PetService petService;
	
	// ? consider commented alterations for next method for use in Spring MVC Web controller?
	// below method now working - routing to results.html page & displays message there if DB empty
	//@GetMapping("/viewAllVolunteers") //method to be run when /viewAllVolunteers link is called
	@GetMapping({"/viewAllVolunteers", "viewAllVolunteers"})
	public String viewAllVolunteers(Model model) {
		//List<Volunteer> volunteers = volunteerService.getAllVolunteers();
		if(volunteerService.getAllVolunteers().isEmpty()) {
			//return addVolunteer(model);
			return "no volunteers found in DB";
		} else {
			model.addAttribute("volunteers", volunteerService.getAllVolunteers());
			// adding below line didn't resolve results page viewAllVolunteers error issues
			//model.addAttribute("selectablePets", petService.getAllPets());
			return "results";
			//return "viewVolunteers"; //? make new/separate pages for viewing Volunteers/Customers/Pets
		}
		
	}
	
	//@GetMapping("/viewAllVolunteers")
	@GetMapping("/volunteers") //?? unsure which version of GetMapping URI should use here
	public List<Volunteer> getAllVolunteers(){
	//public String getAllVolunteers(){
		//List<Volunteer> volunteers = volunteerService.getAllVolunteers()
		// model.addAttribute("volunteers", volunteerService.getAllVolunteers());
		// ??or model.addAttribute("volunteers", volunteerRepo.findAll());
		// ??if do above line code version would need to add Autowired VolunteerRepository to this class
		// return "results";
		return volunteerService.getAllVolunteers();
	}
		
	@GetMapping("/volunteer/{volunteerId}")
	// ?? unsure if should use long id vs long volunteerId in next line - start w/ latter version
	//public Volunteer getVolunteer(@PathVariable("volunteerId") long id) {
	public Volunteer getVolunteer(@PathVariable("volunteerId") long volunteerId) {
		return volunteerService.getVolunteerById(volunteerId);
	}
	
	//?? not sure if above public Volunteer getVolunteer(@PathVariable("volunteerId") long volunteerId) method
	// needs changed to below method for use when editing volunteer in Spring MVC Web controller
	// or if below method just needs to be added to the VolunteerWebController to retrieve data from
	// webpage for editing - if volunteer w/ specified id is not found then create null object to fill
	// with data pulled from webpage
	//@GetMapping("/editVolunteer/{id}")
	@GetMapping("/editVolunteer/{volunteerId}")
	public String editVolunteer(@PathVariable("volunteerId") long volunteerId, Model model) {
		Volunteer volunteer = volunteerService.getVolunteerById(volunteerId);
		// ??would need to change above line to below if can't create method in VolunteerService to address
		// creation of empty Volunteer object to put in data from web page for new Volunteer if 
		// volunteerId that was searched for is not found
		//??Volunteer volunteer = volunteerRepo.findById(volunteerId).orElse(null);
		model.addAttribute("newVolunteer", volunteer);
		//??model.addAttribute("selectablePets", petService.getAllPets()); or need if/else?
		// ?? didn't work in WebController for pets if fosterOwner null
		return "input";
		//return "newVolunteer"; //? make new/separate pages for viewing new Volunteers/Customers/Pets
	}
	
	// trial change below to more specific
	//@PostMapping("/update/{volunteerId}")
	//@PostMapping("volunteer/update/{volunteerId}")
	@PostMapping("/updateVolunteer/{volunteerId}")
	public String updateVolunteer(@ModelAttribute("volunteer") Volunteer volunteer, Model model) {
		volunteerService.saveVolunteerEdit(volunteer);
		// adding below line &/or changing return doesn't resolve issues
		model.addAttribute("volunteer", volunteer);
		return viewAllVolunteers(model);
		//return "results";
	}
	
	//?? re: above @DeleteMapping version of deleteVolunteer method vs below @GetMapping version
	//@DeleteMapping("/delete/{volunteerId}") //?? this or below path???
	@DeleteMapping("/volunteer/{volunteerId}")
	// ?? unsure if should use long id vs long volunteerId in next line - start w/ latter version
	//public void deleteVolunteer(@PathVariable("volunteerId") long id) {
	//public void deleteVolunteer(@PathVariable("volunteerId") long volunteerId) {
	public void volunteerToDelete(@PathVariable("volunteerId") long volunteerId) {
		volunteerService.deleteVolunteerById(volunteerId);
	}
	
	//?? re: above @DeleteMapping version of deleteVolunteer method vs below @GetMapping version
	// that calls it - if used committed out version w/o call to above method line of code will 
	// need to add @Autowired VolunteerRepository volunteerRepo creation
	//@GetMapping("/delete/{volunteerId}")
	@GetMapping("/deleteVolunteer/{volunteerId}")
	public String deleteVolunteer(@PathVariable("volunteerId") long volunteerId, Model model) {
		//Volunteer volunteer = volunteerRepo.findById(volunterId).orElse(null);
		//volunteerRepo.delete(volunteer);
		volunteerService.deleteVolunteerById(volunteerId);
		return viewAllVolunteers(model);
	}
	
	//@GetMapping("/inputVolunteer")
	@GetMapping("/addVolunteer")
	public String inputVolunteer(Model model) {
		Volunteer volunteer = new Volunteer();
		model.addAttribute("newVolunteer", volunteer);
		model.addAttribute("selectablePets", petService.getAllPets());
		return "input";
	}
	
	@PostMapping("/addVolunteer") // ?? this version vs below
//	@PostMapping("/volunteer")
	public String addVolunteer(@ModelAttribute("newVolunteer") Volunteer volunteer, Model model) {
	//public void addVolunteer(@RequestBody Volunteer volunteer) {
		volunteerService.saveVolunteerEdit(volunteer);
		// trial adding model.addAttribute line
		model.addAttribute("newVolunteer", volunteer);
		return viewAllVolunteers(model);
	}
	
	// ?? below two methods are alternatives of above for use in Spring MVC web controller 
	//@GetMapping("/volunteer")
	@GetMapping("/volunteer.html")
	//@GetMapping("/addVolunteer")
	public String addVolunteer(Model model) {
		Volunteer volunteer = new Volunteer();
		model.addAttribute("newVolunteer", volunteer);
		//return "input";
		return "volunteer";
		//return "newVolunteer"; //? make new/separate pages for viewing new Volunteers/Customers/Pets
	}
	
	//@PostMapping("/saveVolunteer") //?trial of changing PostMapping so not same as GetMapping above
	//@PostMapping("/volunteer")
	@PostMapping("/addNewVolunteer")
	public String addNewVolunteer(@ModelAttribute("newVolunteer") Volunteer volunteer, Model model) {
	//public String addVolunteer(@ModelAttribute("volunteer") Volunteer volunteer, Model model) {
	//public String addVolunteer(@ModelAttribute Volunteer volunteer, Model model) {
		//volunteerRepo.save(volunteer); // ?? does below replace this line??
		volunteerService.saveVolunteerEdit(volunteer);
		//return viewAllVolunteers(model);
		//return "volunteer";
		//return "redirect:/success"; //??change to this if after saves want to redirect to success page?? 
		return "success";	//?? if this version of telling it to go to 'success' page doesn't work	
		//return "newVolunteer"; //? make new/separate pages for viewing new Volunteers/Customers/Pets
	}
	
	//??? re: below method
	//@PutMapping("/edit/{volunteerId}")
	//@PutMapping("/volunteer/edit")
//	@PutMapping("/volunteer")
//	public void editVolunteer(@RequestBody Volunteer volunteer) {
//		volunteerService.saveVolunteerEdit(volunteer);
//	}
	
//	//??? above PutMapping version of editVolunteer method need changed to below version for 
//	// ???Spring MVC Webcontroller or both??
//	//@PostMapping("/edit/{volunteerId}")
//	@PostMapping("/update/{volunteerId}")
//	//public String editVolunteer(@RequestBody Volunteer volunteer) {
//	public String editVolunteer(Volunteer volunteer, Model model) {
//		volunteerService.saveVolunteerEdit(volunteer);
//		return viewAllVolunteers(model);
//	}
	
	
	
	// ?? In future create a method that gets a randomly selected volunteer info to display on volunteer page
	// ?? to feature as a volunteer of the month. 
}
