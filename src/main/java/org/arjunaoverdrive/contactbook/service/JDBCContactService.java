package org.arjunaoverdrive.contactbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.contactbook.DAO.ContactRepository;
import org.arjunaoverdrive.contactbook.exception.ContactNotFoundException;
import org.arjunaoverdrive.contactbook.model.Contact;
import org.arjunaoverdrive.contactbook.web.DTO.CreateContactDto;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JDBCContactService implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> list() {
        List<Contact> contacts = new ArrayList<>(contactRepository.findAll());
        return contacts;
    }

    @Override
    public Contact findContactById(Long id) {
        Contact contact = contactRepository
                .findById(id)
                .orElseThrow(() -> new ContactNotFoundException(
                        MessageFormat.format("Contact with id {0} not found.", id)));
        return contact;
    }

    @Override
    public Contact createContact(CreateContactDto dto) {
        Contact contact = Contact
                .builder()
                .phone(dto.phone())
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
        Contact saved = contactRepository.save(contact);
        return saved;
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact toUpdate = findContactById(contact.getId());

        toUpdate.setFirstName(contact.getFirstName() == null ? toUpdate.getFirstName() : contact.getFirstName());
        toUpdate.setLastName(contact.getLastName() == null ? toUpdate.getLastName() : contact.getLastName());
        toUpdate.setEmail(contact.getEmail() == null ? toUpdate.getEmail() : contact.getEmail());
        toUpdate.setPhone(contact.getPhone() == null ? toUpdate.getPhone() : contact.getPhone());

        return contactRepository.update(toUpdate);
    }

    @Override
    public void deleteContactById(Long id) {
        Contact toDelete = findContactById(id);

        contactRepository.delete(toDelete.getId());
    }
}
