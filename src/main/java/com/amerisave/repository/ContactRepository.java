package com.amerisave.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import com.amerisave.model.Contact;

/**
 * @author Nikhila Nervetla
 *
 */
public interface ContactRepository extends CrudRepository<Contact,String>{
	
	public Optional<Contact> findByEmailAddressIgnoreCase(String emailAddress);

}
