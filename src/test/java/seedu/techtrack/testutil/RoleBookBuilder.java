package seedu.techtrack.testutil;

import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.role.Role;

/**
 * A utility class to help with building RoleBook objects.
 * Example usage: <br>
 *     {@code RoleBook ab = new RoleBookBuilder().withRole("John", "Doe").build();}
 */
public class RoleBookBuilder {

    private RoleBook roleBook;

    public RoleBookBuilder() {
        roleBook = new RoleBook();
    }

    public RoleBookBuilder(RoleBook roleBook) {
        this.roleBook = roleBook;
    }

    /**
     * Adds a new {@code Role} to the {@code RoleBook} that we are building.
     */
    public RoleBookBuilder withRole(Role role) {
        roleBook.addRole(role);
        return this;
    }

    public RoleBook build() {
        return roleBook;
    }
}
