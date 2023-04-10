package seedu.internship.model;

import javafx.collections.ObservableList;
import seedu.internship.model.internship.Internship;

/**
 * Unmodifiable view of an InternBuddy
 */
public interface ReadOnlyInternBuddy {

    /**
     * Returns an unmodifiable view of the internships list.
     * This list will not contain any duplicate internships
     */
    ObservableList<Internship> getInternshipList();

}
