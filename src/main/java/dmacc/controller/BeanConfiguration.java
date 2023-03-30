/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Mar 29, 2023
* Group 8 Final Project collaboration
* with Sylwia Glod & Kevin Roney
*/
package dmacc.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dmacc.beans.Customer;
import dmacc.beans.Pet;
import dmacc.beans.Volunteer;


/**
 * Group 8 Final Project
 * Pet Adoption Website Service (P.A.W.S.)
 * Using @Configuration annotation used to tell Spring to 
 * manage the beans.  Using Spring @Bean annotation on method to declare
 * the new bean returned is managed by Spring context
 */
@Configuration
public class BeanConfiguration {
	
	@Bean
	public Pet pet() {
		Pet bean = new Pet();
		return bean;
	}
	
	@Bean
	public Volunteer volunteer() {
		Volunteer bean = new Volunteer();
		return bean;
	}
	
	@Bean
	public Customer customer() {
		Customer bean = new Customer();
		return bean;
	}
	

}
