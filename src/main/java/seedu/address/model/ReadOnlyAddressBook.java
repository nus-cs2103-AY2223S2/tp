package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.job.Role;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the roles list.
     * This list will not contain any duplicate roles.
     */
    ObservableList<Role> getRoleList();

}
