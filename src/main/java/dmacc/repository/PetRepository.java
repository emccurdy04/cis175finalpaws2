/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 29, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Pet;
import dmacc.beans.Volunteer;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.)
 * Repository classes are the DAO interfaces that extends the JpaRepository
 * (or CrudRespository) to handle SQL queries. Mediating between data access
 * layer & business access/service layer. (The Service layer class contains the
 * calls to the methods of the DAO (repo) interface for the SQL operations.) 
 * The @Repository annotation is used to indicate this interface is a repository.
 * It provides the type of entity & its primary key.
 * Using these interfaces gives a lot of built in methods: save( ), findAll( ), 
 * count( ), delete( ), findById( ). 
 */
//JpaRepository<className, Integer> vs JpaRepository<className, Long> saw two 
// different variations in tutorials w/o clear explanation of why Long vs Integer
// but appears if Id related instance variable is Long use long if int use Integer
// Found source that confirmed the id datatype of the domain class should match
// the 2nd argument datatype. 
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	Volunteer findByFosterOwner(long volunteerId);

	List<Pet> findByPetType(String petType);

	List<Pet> findPetByGender(String gender);

	List<Pet> findPetByBreed(String breed);

	//List<Pet> findPetByName(String petName);

}
