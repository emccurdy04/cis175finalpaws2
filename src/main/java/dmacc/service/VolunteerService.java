/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Apr 5, 2023
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
import dmacc.beans.Volunteer;
import dmacc.repository.PetRepository;
import dmacc.repository.VolunteerRepository;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * VolunteerService class uses VolunteerRepository
 * 
 */
@Service
public class VolunteerService {
	
	@Autowired
	VolunteerRepository volunteerRepo;
	
	@Autowired
	PetService petService;
	
	public Volunteer getVolunteerById(long volunteerId) {
		return volunteerRepo.findById(volunteerId).get();
	}
	
	public List<Volunteer> getAllVolunteers(){
		List<Volunteer> volunteers = new ArrayList<Volunteer>();
		volunteerRepo.findAll().forEach(volunteer -> volunteers.add(volunteer));
		return volunteers;
	}
	
	public void saveVolunteerEdit(Volunteer volunteer) {
		volunteerRepo.save(volunteer);
	}
	
	public void deleteVolunteerById(long volunteerId) {
		volunteerRepo.deleteById(volunteerId);
	}
	
//	/**
//	 * Method to search for Volunteer by petId to get volunteer contact info
//	 * for use in future when customer requests time to set up to visit pet
//	 */
//	public Volunteer getVolunteerByPet(long petId) {
//		//Pet pet = petService.getPetById(petId);
//		//if (petId in volunteer.listOfFosterPets)
//		return volunteerRepo.findByFosterPet(petId);
//	}
	
	public Volunteer getVolunteerByEmail(String email) {
		return volunteerRepo.findVolunteerByEmail(email);
	}
	
//	public Pet getFosterPet(Volunteer volunteer) {
//		Pet fosterPet = new Pet();
//		//fosterPet = volunteerRepo.findFosterPet(volunteer);
//		//fosterPet = volunteer.getFosterPet();
//		fosterPet = volunteer.volunteerFosterPet(volunteer);
//		return fosterPet;
//		//return volunteerRepo.getFosterPet(volunteer);
//	}
	
	public Pet getFosterPet(long volunteerId) {
		Volunteer volunteer = volunteerRepo.findById(volunteerId).get();
		return volunteer.getFosterPet();
	}
	
	public Pet getFosterPet(Volunteer volunteer) {
		//Volunteer volunteer = volunteerRepo.findById(volunteerId).get();
		Pet fosterPet = volunteer.getFosterPet();
		return fosterPet;
		//return volunteer.getFosterPet();
	}
	
	// ?? In future create a method that randomly selects a volunteer so volunteer info can
	// ?? be displayed on volunteer page to be featured as the volunteer of the month. 
	//public Volunteer selectRandomVolunteerById() {
		// determine number of volunteerIds in database volunteers table
		// then use as end range number for Math.random() fxn
		// assign randomly selected number to volunteerId
		//long volunteerId = Math.random
		// then find/return the randomly selected Volunteer entity info
		//return volunteerRepo.findById(volunteerId).get();
	//}
	
}
