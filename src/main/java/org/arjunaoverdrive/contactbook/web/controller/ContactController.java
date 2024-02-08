package org.arjunaoverdrive.contactbook.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.contactbook.model.Contact;
import org.arjunaoverdrive.contactbook.service.ContactService;
import org.arjunaoverdrive.contactbook.web.DTO.CreateContactDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @ModelAttribute("contact")
    public Contact contact(){
        return new Contact();
    }

    @GetMapping
    public String index(Model model) {
        List<Contact> contactList = contactService.list();
        model.addAttribute("contacts", contactList);
        return "index";
    }

    @GetMapping("/contacts/{id}")
    public String getContact(@PathVariable Long id, Model model){
        Contact contact = contactService.findContactById(id);
        model.addAttribute("contact", contact);
        return "redirect:/";
    }

    @PostMapping("/contacts/save")
    public String addContact(@ModelAttribute CreateContactDto dto){
        Contact contact = contactService.createContact(dto);
        return "redirect:/";
    }

    @GetMapping("/contacts/{id}/edit")
    public String getEditContactPage(@PathVariable Long id, Model model){
        model.addAttribute("contact", contactService.findContactById(id));
        return "edit";
    }

    @PostMapping("/contacts/edit")
    public String updateContact(@ModelAttribute Contact contact){
        contactService.updateContact(contact);

        return "redirect:/";
    }

    @GetMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable Long id){
        contactService.deleteContactById(id);

        return "redirect:/";
    }
}
