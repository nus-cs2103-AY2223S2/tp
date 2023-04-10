package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.fish.Fish;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withFish("John", "Doe").build();}
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
     * Adds a new {@code Fish} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withFish(Fish fish) {
        addressBook.addFish(fish);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
