package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.tutee.Tutee;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTuteeManagingSystem {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Tutee> getPersonList();

}
