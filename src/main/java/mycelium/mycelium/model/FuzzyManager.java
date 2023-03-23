package mycelium.mycelium.model;

import java.util.logging.Logger;

import javafx.collections.transformation.SortedList;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;

/**
 * This class is responsible for handling fuzzy matching of clients and projects.
 */
public class FuzzyManager {
    private static final Logger logger = LogsCenter.getLogger(FuzzyManager.class);
    private final ReadOnlyAddressBook addressBook;

    /**
     * Initializes a FuzzyManager with the given address book.
     *
     * @param addressBook the address book to use for fuzzy matching.
     */
    public FuzzyManager(ReadOnlyAddressBook addressBook) {
        this.addressBook = addressBook;
    }

    // The two lists we get here from the address book are immutable references. So we can do whatever with them
    // and not worry about messing up the address book's state.
    //
    // NOTE(jy): this is very inefficient, but it is simple and gets the job done for now.
    public SortedList<Client> getFuzzyClients(String query) {
        if (query.isEmpty()) {
            return addressBook.getClientList().sorted();
        }
        logger.info("Received fuzzy match request for client with query = " + query);
        return addressBook.getClientList()
            .filtered(cli -> cli.fuzzyCompareTo(query) > 0.0)
            .sorted((cli1, cli2) -> Double.compare(cli2.fuzzyCompareTo(query), cli1.fuzzyCompareTo(query)));
    }

    public SortedList<Project> getFuzzyProjects(String query) {
        if (query.isEmpty()) {
            return addressBook.getProjectList().sorted();
        }
        logger.info("Received fuzzy match request for projects with query = " + query);
        return addressBook.getProjectList()
            .filtered(proj -> proj.fuzzyCompareTo(query) > 0.0)
            .sorted((proj1, proj2) -> Double.compare(proj2.fuzzyCompareTo(query), proj1.fuzzyCompareTo(query)));
    }
}
