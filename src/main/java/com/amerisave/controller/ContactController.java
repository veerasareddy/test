package com.amerisave.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amerisave.model.Contact;
import com.amerisave.service.ContactService;

/**
 * @author Nikhila Nervetla
 *
 */
@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/contacts")
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}


	@RequestMapping(method = RequestMethod.GET, value ="/contacts/{emailAddress}")
	public Optional<Contact> getContactByEmailAddress(@PathVariable String emailAddress) {
		return contactService.getContactByEmailAddress(emailAddress);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/contacts")
	public ResponseEntity<String> addContact(@RequestBody Contact contact, HttpServletRequest req,
			HttpServletResponse res) {
		contactService.addContact(contact);
		return new ResponseEntity<String>("Created Success", HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/contacts/{emailAddress}")
	public ResponseEntity<String> updateContact(@RequestBody Contact contact, @PathVariable String emailAddress,
			HttpServletRequest req, HttpServletResponse res) {
		contactService.updateContact(contact, emailAddress);
		return new ResponseEntity<String>("Updated Success", HttpStatus.OK);
	}

	@ExceptionHandler
	@RequestMapping(method = RequestMethod.DELETE, value = "/contacts/{emailAddress}")
	public ResponseEntity<String> deleteContact(@PathVariable String emailAddress, HttpServletRequest req,
			HttpServletResponse res) {
		contactService.deleteContact(emailAddress);
		return new ResponseEntity<String>("Deleted the record", HttpStatus.ACCEPTED);
	
	}

}