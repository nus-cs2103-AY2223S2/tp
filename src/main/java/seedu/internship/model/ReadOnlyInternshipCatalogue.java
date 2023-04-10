package seedu.internship.model;

import javafx.collections.ObservableList;
import seedu.internship.model.internship.Internship;

/**
 * Unmodifiable view of an Internship Catalogue.
 */
public interface ReadOnlyInternshipCatalogue {

    /**
     * Returns an unmodifiable view of the internship list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Internship> getInternshipList();
}
