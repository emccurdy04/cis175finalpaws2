package dmacc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dmacc.beans.Pet;
import dmacc.controller.BeanConfiguration;

/**
 * The @SpringBootApplication annotation - combines @Configuration, 
 * @EnableAutoConfiguration, & @ComponentScan annotations. 
 */

@SpringBootApplication
public class Cis175finalpaws2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cis175finalpaws2Application.class, args);
		
		ApplicationContext appContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
		
		//Pet pet = appContext.getBean("pet", Pet.class);
		
		//System.out.println(pet.toString());
		//Example of output: 
		//Pet(petId=0, petName=null, petType=null, breed=null, gender=null, 
		//fosterOwner=Volunteer(volunteerId=0, firstName=null, lastName=null, 
		//street=null, city=null, state=null, zip=null, phone=null))
		
		
		//Volunteer volunteer = appContext.getBean("volunteer", Volunteer.class);
		//System.out.println(volunteer.toString());
		
		//Customer customer = appContext.getBean("customer", Customer.class);
		//System.out.println(customer.toString());
	}

}
