package mycelium.mycelium.model;

import java.util.logging.Logger;

import javafx.collections.transformation.SortedList;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.ui.MainWindow;
import mycelium.mycelium.ui.commandbox.CommandBox;

/**
 * This class is responsible for handling fuzzy matching of clients and projects.
 */
public class FuzzyManager implements CommandBox.CommandInputListener {
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

    @Override
    public void onInputChanged(String input, MainWindow mainWindow) {
        if (input.isEmpty()) {
            mainWindow.setClients(addressBook.getClientList());
            mainWindow.setProjects(addressBook.getProjectList());
            return;
        }
        logger.info("Received fuzzy match request for: " + input);

        // The two lists we get here from the address book are immutable references. So we can do whatever with them
        // and not worry about messing up the address book's state.
        //
        // NOTE(jy): this is very inefficient, but it is simple and gets the job done for now.

        SortedList<Client> clients = addressBook.getClientList()
            .filtered(cli -> cli.fuzzyCompareTo(input) > 0.0)
            .sorted((cli1, cli2) -> Double.compare(cli2.fuzzyCompareTo(input), cli1.fuzzyCompareTo(input)));
        SortedList<Project> projects = addressBook.getProjectList()
            .filtered(proj -> proj.fuzzyCompareTo(input) > 0.0)
            .sorted((proj1, proj2) -> Double.compare(proj2.fuzzyCompareTo(input), proj1.fuzzyCompareTo(input)));

        mainWindow.setClients(clients);
        mainWindow.setProjects(projects);
    }
}
