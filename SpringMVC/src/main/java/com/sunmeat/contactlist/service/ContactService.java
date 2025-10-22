package com.sunmeat.contactlist.service;

import com.sunmeat.contactlist.model.Contact;
import com.sunmeat.contactlist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	public Optional<Contact> getContactById(Long id) {
		return contactRepository.findById(id);
	}

	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
}
