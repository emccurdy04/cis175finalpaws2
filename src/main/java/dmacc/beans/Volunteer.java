/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 29, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.beans;

import java.util.ArrayList;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Group 8 Final Project
 * Pet Adoption Website Service (P.A.W.S.)
 * Lombok @Data annotation bundles - @ToString, @EqualAndHasHashCode, @Getter/@Setter
 * and @RequiredArgsConstructor together. 
 * use JPA's (@Embeddable) annotation at top of classes
 * to declare this class is meant to be embedded by other entities. 
 * every '@Entity' class must declare or inherit at least one '@Id' or '@EmbeddedId' property
 */
@Data
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
//@Embeddable
@Entity(name="volunteers")
@Table(name="volunteers")
public class Volunteer {
	@Id
	//@GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private int volunteerId;
	private long volunteerId;
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
	
	// future addition - since a Volunteer can foster more than 1 pet
	@OneToMany //?(MappedBy='pet')
	//@OneToMany(MappedBy='pet', fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, orphanRemoval=true)
	//@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY, orphanRemoval=true)
	private ArrayList<Pet> listOfFosterPets;
	//?? initialize ArrayList here?
	//private ArrayList<Pet> listOfFosterPets = new ArrayList<>();
	//private Pet fosterPet;
	
//	/**
//	 * Non-Default constructor - takes all parameters except volunteerId & ArrayList<Pet>
//	 * @param firstName
//	 * @param lastName
//	 * @param street
//	 * @param city
//	 * @param state
//	 * @param zip
//	 * @param phone
//	 */
//	public Volunteer(String firstName, String lastName, String street, String city, String state, String zip,
//			String phone) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.zip = zip;
//		this.phone = phone;
//	}
//	
//	/**
//	 * Non-Default constructor - takes all parameters except volunteerId
//	 * @param firstName
//	 * @param lastName
//	 * @param street
//	 * @param city
//	 * @param state
//	 * @param zip
//	 * @param phone
//	 * @param listOfFosterPets
//	 */
//	public Volunteer(String firstName, String lastName, String street, String city, String state, String zip,
//			String phone, ArrayList<Pet> listOfFosterPets) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.zip = zip;
//		this.phone = phone;
//		this.listOfFosterPets = listOfFosterPets;
//	}


	// new Non-Default constructors with new field/variable - email
	/**
	 * Non-Default constructor - takes all parameters except volunteerId & ArrayList<Pet>
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param email
	 */
	public Volunteer(String firstName, String lastName, String street, String city, String state, String zip,
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



	/**
	 * Non-Default constructor - takes all parameters except volunteerId
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param phone
	 * @param email
	 * @param listOfFosterPets
	 */
	public Volunteer(String firstName, String lastName, String street, String city, String state, String zip,
			String phone, String email, ArrayList<Pet> listOfFosterPets) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.listOfFosterPets = listOfFosterPets;
	}
	
	
	/**
	 * constructor/method for adding pet to the listOfFosterPets arraylist
	 * Example 1: One-to-Many association using generics

    // In Customer class:

    @OneToMany(cascade=ALL, mappedBy="customer")
    public Set<Order> getOrders() { return orders; }

    In Order class:

    @ManyToOne
    @JoinColumn(name="CUST_ID", nullable=false)
    public Customer getCustomer() { return customer; }


    Example 2: One-to-Many association without using generics

    // In Customer class:

    @OneToMany(targetEntity=com.acme.Order.class, cascade=ALL,
                mappedBy="customer")
    public Set getOrders() { return orders; }

    // In Order class:

    @ManyToOne
    @JoinColumn(name="CUST_ID", nullable=false)
    public Customer getCustomer() { return customer; }


    Example 3: Unidirectional One-to-Many association using a foreign key mapping

    // In Customer class:

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name="CUST_ID") // join column is in table for Order
    public Set<Order> getOrders() {return orders;} 
	 */
	public void addPet(Pet pet) {
		this.listOfFosterPets.add(pet);
	}

}
