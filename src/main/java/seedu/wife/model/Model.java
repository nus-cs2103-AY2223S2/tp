package seedu.wife.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.wife.commons.core.GuiSettings;
import seedu.wife.model.food.Food;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Food> PREDICATE_SHOW_ALL_FOODS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' WIFE file path.
     */
    Path getWifeFilePath();

    /**
     * Sets the user prefs' WIFE file path.
     */
    void setWifeFilePath(Path wifeFilePath);

    /**
     * Replaces WIFE data with the data in {@code WIFE}.
     */
    void setWife(ReadOnlyWife wife);

    /** Returns the Wife */
    ReadOnlyWife getWife();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasFood(Food food);

    /**
     * Deletes the given food.
     * The food must exist in WIFE.
     */
    void deleteFood(Food target);

    /**
     * Adds the given food.
     * {@code food} must not already exist in WIFE.
     */
    void addFood(Food food);

    /**
     * Replaces the given food {@code target} with {@code editedFood}.
     * {@code target} must exist in WIFE.
     * The food of {@code editedfood} must not be the same as another existing food in WIFE.
     */
    void setFood(Food target, Food editedFood);

    /** Returns an unmodifiable view of the filtered food list */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);
}
