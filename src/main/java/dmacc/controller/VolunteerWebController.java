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
import dmacc.repository.VolunteerRepository;
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
	
	// added below method & ? consider commended alterations for next method for use
	// in Spring MVC Web controller
	@GetMapping("/viewAllVolunteers") //method to be run when /viewAllVolunteers link is called
	// need to create this link on 
	public String viewAllVolunteers(Model model) {
		if(volunteerService.getAllVolunteers().isEmpty()) {
			return addVolunteer(model);
		}
		model.addAttribute("volunteer", volunteerService.getAllVolunteers());
		return "results";
	}
	
	@GetMapping("/viewAllVolunteers")
	//@GetMapping("/volunteers")
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
	
	@DeleteMapping("/volunteer/{volunteerId}")
	// ?? unsure if should use long id vs long volunteerId in next line - start w/ latter version
	//public void deleteVolunteer(@PathVariable("volunteerId") long id) {
	public void deleteVolunteer(@PathVariable("volunteerId") long volunteerId) {
		volunteerService.deleteVolunteerById(volunteerId);
	}
	
	//@PostMapping("/addVolunteer") // ?? this version vs below
	@PostMapping("/volunteer")
	public void addVolunteer(@RequestBody Volunteer volunteer) {
		volunteerService.saveVolunteerEdit(volunteer);
	}
	
	// ?? below two methods are alternatives of above for use in Spring MVC web controller 
	@GetMapping("/addVolunteer")
	public String addVolunteer(Model model) {
		Volunteer volunteer = new Volunteer();
		model.addAttribute("newVolunteer", volunteer);
		return "input";
	}
	
	@PostMapping("/addVolunteer")
	public String addVolunteer(@ModelAttribute("volunteer") Volunteer volunteer, Model model) {
	//public String addVolunteer(@ModelAttribute Volunteer volunteer, Model model) {
		//volunteerRepo.save(volunteer); // ?? does below replace this line??
		volunteerService.saveVolunteerEdit(volunteer);
		return viewAllVolunteers(model);
	}
	
	@PutMapping("/volunteer")
	public void editVolunteer(@RequestBody Volunteer volunteer) {
		volunteerService.saveVolunteerEdit(volunteer);
	}
	
	// ?? In future create a method that gets a randomly selected volunteer info to display on volunteer page
	// ?? to feature as a volunteer of the month. 
}
