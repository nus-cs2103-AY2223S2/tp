package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.application.InternshipApplication;


/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the applications list.
     * This list will not contain any duplicate applications.
     */
    ObservableList<InternshipApplication> getInternshipList();

}
