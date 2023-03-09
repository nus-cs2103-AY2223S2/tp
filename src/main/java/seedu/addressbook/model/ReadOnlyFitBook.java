package seedu.addressbook.model;

import javafx.collections.ObservableList;
import seedu.addressbook.model.client.Client;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFitBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

}
