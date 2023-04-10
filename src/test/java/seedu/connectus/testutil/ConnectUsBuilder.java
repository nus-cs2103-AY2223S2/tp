package seedu.connectus.testutil;

import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ConnectUsBuilder {

    private ConnectUs connectUS;

    public ConnectUsBuilder() {
        connectUS = new ConnectUs();
    }

    public ConnectUsBuilder(ConnectUs connectUS) {
        this.connectUS = connectUS;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ConnectUsBuilder withPerson(Person person) {
        connectUS.addPerson(person);
        return this;
    }

    public ConnectUs build() {
        return connectUS;
    }
}
