package seedu.wife.model;

import javafx.collections.ObservableList;
import seedu.wife.model.food.Food;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyWife {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Food> getFoodList();

}
