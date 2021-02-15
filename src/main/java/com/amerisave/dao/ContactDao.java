/**
 * 
 */
package com.amerisave.dao;

import java.util.List;

import com.amerisave.model.Contact;

/**
 * @author Nikhila Nervetla
 * 
 * This interface is not being used as we connected to Spring JPA Crude repository [Runtime]
 *
 */
public interface ContactDao {
	
	List<Contact> getAllContacts();

	void addContact(Contact contact);

	Contact getContactByEmailAddress(String emailAddress);
	
	Contact getContactByLastName(String lastName);
	
	void updateContact(Contact contact, String emailAddress) ;
	
	void deleteContact(String firstName);

}
