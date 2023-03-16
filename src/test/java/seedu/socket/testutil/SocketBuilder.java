package seedu.socket.testutil;

import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;

/**
 * A utility class to help with building {@code Socket} objects.
 * Example usage: <br>
 *     {@code Socket ab = new SocketBuilder().withPerson("John", "Doe").build();}
 */
public class SocketBuilder {

    private Socket socket;

    public SocketBuilder() {
        socket = new Socket();
    }

    public SocketBuilder(Socket socket) {
        this.socket = socket;
    }

    /**
     * Adds a new {@code Person} to the {@code Socket} that we are building.
     */
    public SocketBuilder withPerson(Person person) {
        socket.addPerson(person);
        return this;
    }

    public Socket build() {
        return socket;
    }
}
