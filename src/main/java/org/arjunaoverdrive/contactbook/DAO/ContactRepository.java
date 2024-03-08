package org.arjunaoverdrive.contactbook.DAO;

import org.arjunaoverdrive.contactbook.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact toUpdate);

    void delete(Long id);

    void batchInsert(List<Contact> contacts);
}
