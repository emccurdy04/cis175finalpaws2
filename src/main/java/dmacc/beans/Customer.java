/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 30, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.beans;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Group 8 Final Project - Pet Adoption Website Service (P.A.W.S.) v2
 * 
 * Customer entity class - object/entity details for what is mapped to the specified DB table.  
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
//??@Embeddable
@Entity
@Table(name="customers")
public class Customer {
	@Id
	//@GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private int customerId;
	private long customerId;
	private String firstName;
	private String lastName;
	private String street;
	//? future add 2nd street for longer addresses
	//private String street2;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	
	//instance variable to hold pet entity/object customer has scheduled to visit with
	//??@Autowired
	@OneToOne
	//@OneToOne(fetch=FetchType.LAZY)//@JoinColumn(name='id')
	//@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, orphanRemoval=true)//@JoinColumn(name='id')
	//@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, orphanRemoval=true)//@JoinColumn(name='petId')
	//@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, orphanRemoval=true)//@JoinColumn(name='petId')
	private Pet selectedPet;
	
	//future add in instance variable to hold date of appointment to see pet
	//private LocalDate visitDate;
	//?? have date & time of appointment separate or in same field/variable??

	
//	/**
//	 * Non-Default constructor - takes all parameters except customerId
//	 * @param firstName
//	 * @param lastName
//	 * @param street
//	 * @param city
//	 * @param state
//	 * @param zip
//	 * @param phone
//	 * @param selectedPet
//	 */
//	public Customer(String firstName, String lastName, String street, String city, String state, String zip,
//			String phone, Pet selectedPet) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.zip = zip;
//		this.phone = phone;
//		this.selectedPet = selectedPet;
//	}

	//new constructors after added email field/variable
	/**
	 * Non-Default constructor - takes all parameters except customerId
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param email
	 * @param selectedPet
	 */
	public Customer(String firstName, String lastName, String street, String city, String state, String zip,
			String phone, String email, Pet selectedPet) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.selectedPet = selectedPet;
	}

	/**
	 * Non-Default constructor - takes all parameters except customerId & selectedPet
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param email
	 */
	public Customer(String firstName, String lastName, String street, String city, String state, String zip,
			String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}


}
