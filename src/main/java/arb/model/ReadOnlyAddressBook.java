package arb.model;

import arb.model.client.Client;
import arb.model.project.Project;
import arb.model.tag.TagMapping;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();

    /**
     * Returns an unmodifiable view of the tag mappings list.
     * This list will not contain any duplicate tag mappings.
     */
    ObservableList<TagMapping> getTagMappingList();
}
