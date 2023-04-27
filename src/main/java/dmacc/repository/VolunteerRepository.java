/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 30, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

	Volunteer findVolunteerByEmail(String email);

	//Volunteer findByFosterPet(long petId);
}
