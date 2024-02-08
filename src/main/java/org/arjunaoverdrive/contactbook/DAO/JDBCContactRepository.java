package org.arjunaoverdrive.contactbook.DAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.contactbook.mapper.ContactRowMapper;
import org.arjunaoverdrive.contactbook.model.Contact;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JDBCContactRepository implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        log.debug("Invoking JDBCContactRepository -> findAll method");

        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("Invoking JDBCContactRepository -> findById method with ID: {}", id);

        String sql = "SELECT * FROM contact WHERE contact.id = ?";

        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Invoking JDBCContactRepository -> save method with contact: {}", contact);

        synchronized (this) {
            Long id = System.currentTimeMillis();
            contact.setId(id);
        }

        String sql = "INSERT INTO contact (first_name, last_name, email, phone, id) VALUES (?, ?, ? ,? , ?)";

        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());

        return contact;
    }

    @Override
    public Contact update(Contact toUpdate) {
        log.debug("Invoking JDBCContactRepository -> update method with contact: {}", toUpdate);

        String sql = "UPDATE contact SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(sql, toUpdate.getFirstName(), toUpdate.getLastName(), toUpdate.getEmail(), toUpdate.getPhone(),
                toUpdate.getId());

        log.info("Successfully updated contact {}", toUpdate);
        return toUpdate;
    }

    @Override
    public void delete(Long id) {
        log.debug("Invoking JDBCContactRepository -> delete method with id: {}.", id);

        String sql = "DELETE FROM contact WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        log.debug("Invoking JDBCContactRepository -> batchInsert method.");

        String sql = "INSERT INTO contact (first_name, last_name, email, phone, id) VALUES (?, ?, ? ,? , ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contact contact = contacts.get(i);
                ps.setString(1, contact.getFirstName());
                ps.setString(2, contact.getLastName());
                ps.setString(3, contact.getEmail());
                ps.setString(4, contact.getPhone());
                ps.setLong(5, contact.getId());
            }

            @Override
            public int getBatchSize() {
                return contacts.size();
            }
        });
    }


}
