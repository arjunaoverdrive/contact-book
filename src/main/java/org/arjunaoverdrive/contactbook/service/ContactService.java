package org.arjunaoverdrive.contactbook.service;

import org.arjunaoverdrive.contactbook.model.Contact;
import org.arjunaoverdrive.contactbook.web.DTO.CreateContactDto;

import java.util.List;

public interface ContactService {
    List<Contact> list();

    Contact findContactById(Long id);

    Contact createContact(CreateContactDto dto);

    Contact updateContact(Contact contact);

    void deleteContactById(Long id);
}
