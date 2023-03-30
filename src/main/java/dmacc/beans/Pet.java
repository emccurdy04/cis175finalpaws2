/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 29, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.beans;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.)
 * 
 * Pet entity class - object/entity details for what is mapped to the specified DB table.  
 * Lombok annotations: @Data annotation bundles - @ToString, @EqualAndHasHashCode,
 * @Getter/@Setter and @RequiredArgsConstructor together. 
 * Lombok @Builder annotation: instructs Lombok to implement the Builder design 
 * pattern for the POJO class (would create this method in a class to load default data
 * into the DB on application start up).
 * Lombok annotations: @NoArgsConstructor & @AllArgsConstructor - self explanatory.
 * Lombok @Slf4j annotation: causes Lombok to generate a logger field. 
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
// JPA annotation - @Entity, @Table, @Id, @GeneratedValue, @Column
@Entity(name="pets")
@Table(name="pets")
//?@Component //additional Spring framework annotation shown in some tutorials
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//??multiple tutorials showed long rather than int for Id, ?& different 2nd
	//repo arg - JpaRepository<className, Integer> vs JpaRepository<className, Long>
	//could not find explanation for one vs other - switched to long to match repo
	//arg??
	//private int petId;
	private long petId;
	private String petName;
	private String petType;
	private String breed;
	private String gender;
	
	@Autowired //??
	//@ManyToOne
	//@ManyToOne(fetch=FetchType.LAZY)//@JoinColumn(name='volunteerId')
	private Volunteer fosterOwner;
	
	//future stages
	//private LocalDate DOB;
	
	//@Transient //temporal instance variable for calculated pet age
	//private int age //?? int vs String ??
	
	//future stages 'blob' variable for pet picture stored in DB
	
	//future stages generalized string for pet facts??
	// ??likely not search-able so will probably have to list
	// individual search points - good with kids, house broken,
	// health issues etc..
	//private String petFacts;
	
	//?? 2 below instance variables ?N/A for some pet types
	//?? consider if can get parent child class working
	//private String vaccinated;
	//private String sterilized;
	
	
	/**
	 * Non-Default constructor - takes all parameters except petId & fosterOwner
	 * @param petName
	 * @param petType
	 * @param breed
	 * @param gender
	 */
	public Pet(String petName, String petType, String breed, String gender) {
		super();
		this.petName = petName;
		this.petType = petType;
		this.breed = breed;
		this.gender = gender;
	}

	/**
	 * Non-Default constructor - takes all parameters except petId
	 * @param petName
	 * @param petType
	 * @param breed
	 * @param gender
	 * @param fosterOwner
	 */
	public Pet(String petName, String petType, String breed, String gender, Volunteer fosterOwner) {
		super();
		this.petName = petName;
		this.petType = petType;
		this.breed = breed;
		this.gender = gender;
		this.fosterOwner = fosterOwner;
	}
	
	// helper methods
	// future stages method to get current date, pet DOB & use them
	// to calculate current pet age
		

}
