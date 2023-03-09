package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;

/**
 * Unmodifiable view of an Internship Catalouge
 */
public interface ReadOnlyInternshipCatalogue {

    /**
     * Returns an unmodifiable view of the internship list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Internship> getInternshipList();

}