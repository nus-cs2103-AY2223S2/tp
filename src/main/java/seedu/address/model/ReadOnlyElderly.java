package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Elderly;

public interface ReadOnlyElderly {

    /**
     * Returns an unmodifiable view of the elderly list.
     * This list will not contain any duplicate elderly.
     */
    ObservableList<Elderly> getElderlyList();
}
