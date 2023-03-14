package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.tutee.Tutee;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Tutee> PREDICATE_SHOW_ALL_TUTEES = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getTuteeManagingSystemPath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setTuteeManagingSystemPath(Path tuteeManagingSystemPath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setTuteeManagingSystem(ReadOnlyTuteeManagingSystem tuteeManagingSystem);

    /** Returns the TuteeManagingSystem */
    ReadOnlyTuteeManagingSystem getTuteeManagingSystem();

    /**
     * Returns true if a tutee with the same identity as {@code tutee} exists in the address book.
     */
    boolean hasTutee(Tutee tutee);

    /**
     * Deletes the given tutee.
     * The tutee must exist in the address book.
     */
    void deleteTutee(Tutee target);

    /**
     * Adds the given tutee.
     * {@code tutee} must not already exist in the address book.
     */
    void addTutee(Tutee tutee);

    /**
     * Replaces the given tutee {@code target} with {@code editedTutee}.
     * {@code target} must exist in the address book.
     * The tutee identity of {@code editedTutee} must not be the same as another existing tutee in the address book.
     */
    void setTutee(Tutee target, Tutee editedTutee);

    /** Returns an unmodifiable view of the filtered tutee list */
    ObservableList<Tutee> getFilteredTuteeList();

    /**
     * Updates the filter of the filtered tutee list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTuteeList(Predicate<Tutee> predicate);
}
