package seedu.roles.model;

import javafx.collections.ObservableList;
import seedu.roles.model.job.Role;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRoleBook {

    /**
     * Returns an unmodifiable view of the roles list.
     * This list will not contain any duplicate roles.
     */
    ObservableList<Role> getRoleList();
}
