package seedu.techtrack.model;

import javafx.collections.ObservableList;
import seedu.techtrack.model.role.Role;

/**
 * Unmodifiable view of an setRoleBookFilePath book
 */
public interface ReadOnlyRoleBook {

    /**
     * Returns an unmodifiable view of the roles list.
     * This list will not contain any duplicate roles.
     */
    ObservableList<Role> getRoleList();
}
