package seedu.address.testutil;

import seedu.address.model.HMHero;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private HMHero HMHero;

    public AddressBookBuilder() {
        HMHero = new HMHero();
    }

    public AddressBookBuilder(HMHero HMHero) {
        this.HMHero = HMHero;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        HMHero.addPerson(person);
        return this;
    }

    public HMHero build() {
        return HMHero;
    }
}
