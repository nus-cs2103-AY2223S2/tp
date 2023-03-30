package seedu.address.testutil;

import seedu.address.model.ModuleTracker;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withModule("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ModuleTracker addressBook;

    public AddressBookBuilder() {
        addressBook = new ModuleTracker();
    }

    public AddressBookBuilder(ModuleTracker addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Module} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withModule(Module module) {
        addressBook.addModule(module);
        return this;
    }

    public ModuleTracker build() {
        return addressBook;
    }
}
