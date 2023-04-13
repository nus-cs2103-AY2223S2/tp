package mycelium.mycelium.model;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {
    ObservableList<Client> getClientList();

    ObservableList<Project> getProjectList();
}
