package seedu.fitbook.model;

import javafx.collections.ObservableList;
import seedu.fitbook.model.client.Client;

/**
 * Unmodifiable view of FitBook
 */
public interface ReadOnlyFitBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

}
