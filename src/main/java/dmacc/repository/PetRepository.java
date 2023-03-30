/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 29, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Pet;
import dmacc.beans.Volunteer;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.)
 * Repository classes are the DAO interfaces that extends the JpaRepository
 * (or CrudRespository) to handle SQL queries. 
 * The @Repository annotation is used to indicate this interface is a repository.
 * It provides the type of entity & its primary key.
 * Using these interfaces gives a lot of built in methods: save( ), findAll( ), 
 * count( ), delete( ), findById( ). 
 */
//JpaRepository<className, Integer> vs JpaRepository<className, Long> saw two 
// different variations in tutorials w/o clear explanation of why Long vs Integer
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
