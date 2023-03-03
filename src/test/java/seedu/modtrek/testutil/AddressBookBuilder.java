package seedu.modtrek.testutil;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private DegreeProgression addressBook;

    public AddressBookBuilder() {
        addressBook = new DegreeProgression();
    }

    public AddressBookBuilder(DegreeProgression addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public DegreeProgression build() {
        return addressBook;
    }
}
