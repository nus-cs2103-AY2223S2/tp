package mycelium.mycelium.model;

import java.util.logging.Logger;

import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.util.Fuzzy;
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
    public void onInputChanged(String rawInput, MainWindow mainWindow) {
        if (rawInput.isEmpty()) {
            mainWindow.setClients(addressBook.getClientList());
            mainWindow.setProjects(addressBook.getProjectList());
            return;
        }
        logger.info("Received fuzzy match request for: " + rawInput);

        // The two lists we get here from the address book are immutable references. So we can do whatever with them
        // and not worry about messing up the address book's state.
        //
        // NOTE(jy): this is very inefficient, but it is simple and gets the job done for now.

        var input = rawInput.toLowerCase();
        var clients = addressBook.getClientList()
            .filtered(client -> Fuzzy.deltaPercent(input, client.getName().toString().toLowerCase()) > 0.0)
            .sorted((client1, client2) -> {
                var delta1 = Fuzzy.deltaPercent(input, client1.getName().toString().toLowerCase());
                var delta2 = Fuzzy.deltaPercent(input, client2.getName().toString().toLowerCase());
                return Double.compare(delta2, delta1);
            });
        var projects = addressBook.getProjectList()
            .filtered(project -> Fuzzy.deltaPercent(input, project.getName().toString().toLowerCase()) > 0.0)
            .sorted((project1, project2) -> {
                var delta1 = Fuzzy.deltaPercent(input, project1.getName().toString().toLowerCase());
                var delta2 = Fuzzy.deltaPercent(input, project2.getName().toString().toLowerCase());
                return Double.compare(delta2, delta1);
            });

        mainWindow.setClients(clients);
        mainWindow.setProjects(projects);
    }
}
