package org.arjunaoverdrive.contactbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return String.format("%-35d%-15s%-15s%-20s%-15s", id, firstName, lastName, email, phone);
    }
}
