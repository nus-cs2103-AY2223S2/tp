package expresslibrary.testutil;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ExpressLibraryBuilder {

    private ExpressLibrary addressBook;

    public ExpressLibraryBuilder() {
        addressBook = new ExpressLibrary();
    }

    public ExpressLibraryBuilder(ExpressLibrary addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ExpressLibraryBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public ExpressLibrary build() {
        return addressBook;
    }
}
