package arb.testutil;

import arb.model.AddressBook;
import arb.model.client.Client;
import arb.model.project.Project;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withClient("John", "Doe").build();}
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
     * Adds a new {@code Client} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withClient(Client client) {
        addressBook.addClient(client);
        return this;
    }

    /**
     * Adds a new {@code Project} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withProject(Project project) {
        addressBook.addProject(project);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
