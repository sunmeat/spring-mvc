package com.sunmeat.contactlist.controller;

import com.sunmeat.contactlist.model.Contact;
import com.sunmeat.contactlist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // цей клас є контролером Spring MVC, який обробляє http запити
@RequestMapping("/contacts") // встановлення базового url для всіх методів у цьому контролері
public class ContactController {

	@Autowired // автоматичне впровадження екземпляра ContactService в контролер
	private ContactService contactService;

	// анотація робить dependency injection (впровадження залежностей)
	// public ContactController(ContactService contactService) {
	// this.contactService = contactService;
	// }

	@GetMapping // обробка HTTP GET-запиту за url /contacts
	public String listContacts(Model model) {
		model.addAttribute("contacts", contactService.getAllContacts()); // додавання списку контактів у модель
		return "contact-list"; // повернення імені представлення (шаблону), яке буде відображено
	}

	@GetMapping("/add") // обробка GET-запиту за url /contacts/add
	public String addContactForm(Model model) {
		model.addAttribute("contact", new Contact()); // створення нового об'єкта Contact і додавання його в модель
		return "contact-form"; // повернення імені подання для форми додавання контакта
	}

	@PostMapping("/add") // обробка POST-запиту за url /contacts/add
	public String addContact(@ModelAttribute Contact contact) {
		contactService.saveContact(contact); // збереження нового контакта в БД
		return "redirect:/contacts"; // перенаправлення на сторінку зі списком контактів
	}

	@GetMapping("/edit/{id}") // обробка GET-запиту за url /contacts/edit/{id}
	public String editContactForm(@PathVariable("id") Long id, Model model) {
		Optional<Contact> contact = contactService.getContactById(id); // отримання контакта за його id
		if (contact.isPresent()) { // перевірка, чи існує контакт
			model.addAttribute("contact", contact.get()); // додавання контакта в модель для редагування
			return "contact-form"; // повернення імені представлення для форми редагування контакта
		} else {
			return "redirect:/contacts"; // якщо контакт не знайдено, перенаправлення на сторінку зі списком контактів
		}
	}

	@PostMapping("/edit/{id}") // обробка POST-запиту за url /contacts/edit/{id}
	public String editContact(@PathVariable("id") Long id, @ModelAttribute Contact contact) {
		contact.setId(id); // встановлення id контакта для оновлення існуючого контакта
		contactService.saveContact(contact); // збереження оновленого контакта в базі даних
		return "redirect:/contacts"; // перенаправлення на сторінку зі списком контактів
	}

	@GetMapping("/delete/{id}") // обробка GET-запиту за url /contacts/delete/{id}
	public String deleteContact(@PathVariable("id") Long id) {
		contactService.deleteContact(id); // видалення контакта з бази даних за його id
		return "redirect:/contacts"; // перенаправлення на сторінку зі списком контактів
	}
}
