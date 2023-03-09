package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.opening.Opening;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyUltron {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate openings.
     */
    ObservableList<Opening> getOpeningList();

}
