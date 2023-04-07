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

import dmacc.beans.Pet;
import dmacc.beans.Volunteer;
import dmacc.repository.PetRepository;



/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * PetService class uses PetRepository
 */
@Service
public class PetService {
	@Autowired
	PetRepository petRepo;
	
	public Pet getPetById(long petId) {
		return petRepo.findById(petId).get();
	}
	
	public List<Pet> getAllPets(){
		List<Pet> pets = new ArrayList<Pet>();
		petRepo.findAll().forEach(pet -> pets.add(pet));
		return pets;
	}
	
	public void savePetEdit(Pet pet) {
		petRepo.save(pet);
	}
	
	public void deletePetById(long petId) {
		petRepo.deleteById(petId);
	}
	
	// Two method for same delete pet from DB by petId
//	public void deletePetById(long petId) {
//		Pet pet = petRepo.findById(petId).orElse(null);
//		petRepo.delete(pet);
//	}
	
	/**
	 * Method to search for Volunteer by volunteerId to get volunteer contact info
	 * for use in future when customer requests time to set up to visit pet
	 */
	public Volunteer getFosterOwner(long volunteerId) {
		return petRepo.findByFosterOwner(volunteerId);
	}
	
	/**
	 * Methods below for searching pet table by specified category/field value
	 */
	public List<Pet> getPetByType(String petType) {
		//List<Pet> pets = new ArrayList<Pet>();
		//petRepo.findAll()
		return petRepo.findByPetType(petType);
	}
	
	public List<Pet> getPetByGender(String gender){
		return petRepo.findPetByGender(gender);
	}
	
	public List<Pet> getPetByBreed(String breed){
		return petRepo.findPetByBreed(breed);
	}
	
//	public List<Pet> getPetByName(String petName){
//		return petRepo.findPetByName(petName);
//	}

}
