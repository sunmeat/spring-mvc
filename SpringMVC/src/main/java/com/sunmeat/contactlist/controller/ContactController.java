package com.sunmeat.contactlist.controller;

import com.sunmeat.contactlist.model.Contact;
import com.sunmeat.contactlist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // этот класс является контроллером Spring MVC
@RequestMapping("/contacts") // устанавка базового URL для всех методов в этом контроллере
public class ContactController {

    @Autowired // автоматическое внедрение экземпляра ContactService в контроллер
    private ContactService contactService;

    // аннотация делает то самое Dependency Injection
    // public ContactController(ContactService contactService) {
    //     this.contactService = contactService;
    // }
    
    @GetMapping // обработка HTTP GET запроса по URL /contacts
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts()); // добавление списка контактов в модель
        return "contact-list"; // возврат имени представления (шаблона), которое будет отображено
    }

    @GetMapping("/add") // обработка GET запроса по URL /contacts/add
    public String addContactForm(Model model) {
        model.addAttribute("contact", new Contact()); // создание нового объекта Contact и добавляет его в модель
        return "contact-form"; // возврат имени представления для формы добавления контакта
    }

    @PostMapping("/add") // обработка POST запроса по URL /contacts/add
    public String addContact(@ModelAttribute Contact contact) {
        contactService.saveContact(contact); // сохранение нового контакта в базе данных
        return "redirect:/contacts"; // перенаправление на страницу со списком контактов
    }

    @GetMapping("/edit/{id}") // обработка GET запроса по URL /contacts/edit/{id}
    public String editContactForm(@PathVariable Long id, Model model) {
        Optional<Contact> contact = contactService.getContactById(id); // получение контакта по его ID
        if (contact.isPresent()) { // проверка, существует ли контакт
            model.addAttribute("contact", contact.get()); // добавление контакта в модель для редактирования
            return "contact-form"; // возврат имя представления для формы редактирования контакта
        } else {
            return "redirect:/contacts"; // если контакт не найден, перенаправляет на страницу со списком контактов
        }
    }

    @PostMapping("/edit/{id}") // обработка POST запроса по URL /contacts/edit/{id}
    public String editContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id); // установка ID контакта, чтобы обновить существующий контакт
        contactService.saveContact(contact); // сохранение обновленного контакта в базе данных
        return "redirect:/contacts"; // перенаправление на страницу со списком контактов
    }

    @GetMapping("/delete/{id}") // обработка GET запроса по URL /contacts/delete/{id}
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id); // удаление контакта из базы данных по его ID
        return "redirect:/contacts"; // перенаправка на страницу со списком контактов
    }
}