package com.amerisave.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amerisave.model.Contact;
import com.amerisave.repository.ContactRepository;

/**
 * @author Nikhila Nervetla
 *
 */
@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	// Runtime DB innitialized temperarily
	//private ContactDao contactDao = new ContactDaoImpl();

	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<>();
		 contactRepository.findAll()
				.forEach(contacts::add);
		 return contacts;
	}

	public Optional<Contact> getContactByEmailAddress(String emailAddress) {
		return contactRepository.findByEmailAddressIgnoreCase(emailAddress);
	}

	
	public void addContact(Contact contact) {
		contactRepository.save(contact);
	}

	public void updateContact(Contact contact, String emailAddress) {
		contactRepository.save(contact);
	}

	public void deleteContact(String emailAddress) {
		contactRepository.deleteById(emailAddress);
		
	}

}
