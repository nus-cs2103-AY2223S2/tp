package arb.testutil;

import static arb.testutil.TypicalClients.getTypicalClients;
import static arb.testutil.TypicalProjects.getTypicalProjects;

import arb.model.AddressBook;
import arb.model.client.Client;
import arb.model.project.Project;

/**
 * A utility class containing both a list of {@code Client} objects
 * and a list of {@code Project} objects to be used in tests.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients and projects.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }
}
