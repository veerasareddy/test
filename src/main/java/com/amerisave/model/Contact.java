/**
 * 
 */
package com.amerisave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Nikhila Nervetla
 *
 */
@Entity
public class Contact {

	@Id
	@Column(name="email_Address")
	private String emailAddress;	
	
	@Column(name="first_Name")
	private String firstName;
	@Column(name="last_Name")
	private String lastName;


	/* No-arg constructor */
	public Contact() {
		
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 */
	public Contact(String firstName, String lastName, String emailAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
