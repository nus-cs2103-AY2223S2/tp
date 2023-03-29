package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;

/**
 * Unmodifiable view of an internship book
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public interface ReadOnlyInternshipBook {

    /**
     * Returns an unmodifiable view of the applications list.
     * This list will not contain any duplicate Applications.
     */
    ObservableList<Application> getApplicationList();

}
