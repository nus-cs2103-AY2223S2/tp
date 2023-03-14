package trackr.testutil;

import trackr.model.AddressBook;
import trackr.model.supplier.Supplier;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Supplier} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withSupplier(Supplier person) {
        addressBook.addSupplier(person);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
