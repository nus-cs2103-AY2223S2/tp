package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Elderly;

/**
 * Unmodifiable list of Elderly.
 */
public interface ReadOnlyElderly {
    /**
     * Returns an unmodifiable view of the elderly list.
     * This list will not contain any duplicate elderly.
     *
     * @return Unmodifiable elderly list.
     */
    ObservableList<Elderly> getElderlyList();
}
