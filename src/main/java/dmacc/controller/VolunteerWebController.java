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
	
	// added below method & ? consider commented alterations for next method for use
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
	//@GetMapping("/volunteers") //?? unsure which version of GetMapping URI should use here
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
	@GetMapping("/edit/{volunteerId}")
	public String editVolunteer(@PathVariable("volunteerId") long volunteerId, Model model) {
		Volunteer volunteer = volunteerService.getVolunteerById(volunteerId);
		// ??would need to change above line to below if can't create method in VolunteerService to address
		// creation of empty Volunteer object to put in data from web page for new Volunteer if 
		// volunteerId that was searched for is not found
		//??Volunteer volunteer = volunteerRepo.findById(volunteerId).orElse(null);
		model.addAttribute("newVolunteer", volunteer);
		return "input";
	}
	
	//?? re: above @DeleteMapping version of deleteVolunteer method vs below @GetMapping version
	@DeleteMapping("/volunteer/{volunteerId}")
	// ?? unsure if should use long id vs long volunteerId in next line - start w/ latter version
	//public void deleteVolunteer(@PathVariable("volunteerId") long id) {
	public void deleteVolunteer(@PathVariable("volunteerId") long volunteerId) {
		volunteerService.deleteVolunteerById(volunteerId);
	}
	
	//?? re: above @DeleteMapping version of deleteVolunteer method vs below @GetMapping version
	// that calls it - if used committed out version w/o call to above method line of code will 
	// need to add @Autowired VolunteerRepository volunteerRepo creation
	@GetMapping("/delete/{volunteerId}")
	public String deleteVolunteer(@PathVariable("volunteerId") long volunteerId, Model model) {
		//Volunteer volunteer = volunteerRepo.findById(volunterId).orElse(null);
		//volunteerRepo.delete(volunteer);
		volunteerService.deleteVolunteerById(volunteerId);
		return viewAllVolunteers(model);
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
	
	//??? re: below method
	@PutMapping("/volunteer")
	public void editVolunteer(@RequestBody Volunteer volunteer) {
		volunteerService.saveVolunteerEdit(volunteer);
	}
	
	//??? above PutMapping version of editVolunteer method need changed to below version for 
	// ???Spring MVC Webcontroller or both??
	@PostMapping("/edit/{volunteerId}")
	//public String editVolunteer(@RequestBody Volunteer volunteer) {
	public String editVolunteer(Volunteer volunteer, Model model) {
		volunteerService.saveVolunteerEdit(volunteer);
		return viewAllVolunteers(model);
	}
	
	
	
	// ?? In future create a method that gets a randomly selected volunteer info to display on volunteer page
	// ?? to feature as a volunteer of the month. 
}
