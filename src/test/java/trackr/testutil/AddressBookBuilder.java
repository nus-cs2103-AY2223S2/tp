package trackr.testutil;

import trackr.model.SupplierList;
import trackr.model.supplier.Supplier;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private SupplierList addressBook;

    public AddressBookBuilder() {
        addressBook = new SupplierList();
    }

    public AddressBookBuilder(SupplierList addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Supplier} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withSupplier(Supplier person) {
        addressBook.addSupplier(person);
        return this;
    }

    public SupplierList build() {
        return addressBook;
    }
}
