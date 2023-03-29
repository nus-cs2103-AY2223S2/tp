package seedu.wife.model;

import javafx.collections.ObservableList;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyWife {

    /**
     * Returns an unmodifiable view of the foods list.
     * This list will not contain any duplicate foods.
     */
    ObservableList<Food> getFoodList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

}
