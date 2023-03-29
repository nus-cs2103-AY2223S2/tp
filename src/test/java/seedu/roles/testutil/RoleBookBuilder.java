package seedu.roles.testutil;

import seedu.roles.model.RoleBook;
import seedu.roles.model.job.Role;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new RoleBookBuilder().withRole("John", "Doe").build();}
 */
public class RoleBookBuilder {

    private RoleBook roleBook;

    public RoleBookBuilder() {
        roleBook = new RoleBook();
    }

    public RoleBookBuilder(RoleBook addressBook) {
        this.roleBook = addressBook;
    }

    /**
     * Adds a new {@code Role} to the {@code AddressBook} that we are building.
     */
    public RoleBookBuilder withRole(Role role) {
        roleBook.addRole(role);
        return this;
    }

    public RoleBook build() {
        return roleBook;
    }
}
