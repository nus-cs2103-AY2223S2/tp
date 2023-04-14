package seedu.ultron.model;

import javafx.collections.ObservableList;
import seedu.ultron.model.opening.Opening;

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
