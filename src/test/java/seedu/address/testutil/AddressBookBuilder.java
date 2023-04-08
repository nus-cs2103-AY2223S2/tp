package seedu.address.testutil;

import seedu.address.model.PowerConnect;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PowerConnect powerConnect;

    public AddressBookBuilder() {
        powerConnect = new PowerConnect();
    }

    public AddressBookBuilder(PowerConnect powerConnect) {
        this.powerConnect = powerConnect;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        powerConnect.addPerson(person);
        return this;
    }

    public PowerConnect build() {
        return powerConnect;
    }
}
