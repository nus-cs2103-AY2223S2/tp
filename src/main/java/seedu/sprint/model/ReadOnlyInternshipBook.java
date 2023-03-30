package seedu.sprint.model;

import javafx.collections.ObservableList;
import seedu.sprint.model.application.Application;

/**
 * Unmodifiable view of an internship book.
 */
public interface ReadOnlyInternshipBook {

    /**
     * Returns an unmodifiable view of the applications list.
     * This list will not contain any duplicate Applications.
     */
    ObservableList<Application> getApplicationList();

}
