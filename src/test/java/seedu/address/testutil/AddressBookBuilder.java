package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.job.Role;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withRole("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Role} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withRole(Role role) {
        addressBook.addRole(role);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
