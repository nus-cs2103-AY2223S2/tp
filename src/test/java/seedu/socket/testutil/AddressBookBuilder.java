package seedu.socket.testutil;

import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Socket ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Socket socket;

    public AddressBookBuilder() {
        socket = new Socket();
    }

    public AddressBookBuilder(Socket socket) {
        this.socket = socket;
    }

    /**
     * Adds a new {@code Person} to the {@code Socket} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        socket.addPerson(person);
        return this;
    }

    public Socket build() {
        return socket;
    }
}
