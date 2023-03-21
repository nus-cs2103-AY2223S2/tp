package seedu.socket.testutil;

import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

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

    /**
     * Adds a new {@code Project} to the {@code Socket} that we are building.
     */
    public SocketBuilder withProject(Project project) {
        socket.addProject(project);
        return this;
    }

    public Socket build() {
        return socket;
    }
}
