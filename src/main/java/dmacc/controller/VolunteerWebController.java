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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
//@RequestMapping(path = "/volunteer")	//?? unsure if need this line - leave commented out for now
public class VolunteerWebController {
	
	@Autowired
	VolunteerService volunteerService;
	
	@GetMapping("/volunteers")
	public List<Volunteer> getAllVolunteers(){
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
	
	@PostMapping("/volunteer")
	public void addVolunteer(@RequestBody Volunteer volunteer) {
		volunteerService.saveVolunteerEdit(volunteer);
	}
	
	@PutMapping("/volunteer")
	public void editVolunteer(@RequestBody Volunteer volunteer) {
		volunteerService.saveVolunteerEdit(volunteer);
	}
	
	// ?? In future create a method that gets a randomly selected volunteer info to display on volunteer page
	// ?? to feature as a volunteer of the month. 
}
