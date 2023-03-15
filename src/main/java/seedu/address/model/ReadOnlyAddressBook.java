package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.fish.Fish;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the Fishes list.
     * This list will not contain any duplicate Fishes.
     */
    ObservableList<Fish> getFishList();

}
