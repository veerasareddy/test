/**
 * 
 */
package com.amerisave.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amerisave.model.Contact;

/**
 * @author Nikhila Nervetla
 *
 */
public class ContactDaoImpl implements ContactDao {

	/* Runtime DB initializing with some data */
	List<Contact> contacts = new ArrayList<>(Arrays.asList(new Contact("John", "Smith", "john.smith@gmail.com"),
			new Contact("Robert", "Walker", "robert.walker@gmail.com"),
			new Contact("Aarush", "Sareddy", "aarush.sareddy@gmail.com"),
			new Contact("Vasi", "Vempalli", "vasi.vempalli@gmail.com")

	));

	public void addContact(Contact contact) {
		contacts.add(contact);
	}

	public List<Contact> getAllContacts() {
		return contacts;
	}

	public Contact getContactByLastName(String lastName) {
		return contacts.stream().filter(t -> t.getLastName().equalsIgnoreCase(lastName)).findFirst().get();
	}

	public void updateContact(Contact contact, String emailAddress) {
		for (int i = 0; i < contacts.size(); i++) {
			Contact c = contacts.get(i);
			if (c.getEmailAddress().equalsIgnoreCase(emailAddress)) {
				contacts.set(i, contact);
				return;
			}
		}

	}

	public void deleteContact(String emailAddress) {
		contacts.removeIf(c -> c.getEmailAddress().equalsIgnoreCase(emailAddress));
	}


	public Contact getContactByEmailAddress(String emailAddress) {
		return contacts.stream().filter(t -> t.getEmailAddress().equalsIgnoreCase(emailAddress)).findFirst().get();
	}

}
