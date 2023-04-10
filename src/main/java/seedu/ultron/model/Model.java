package seedu.ultron.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.ultron.commons.core.GuiSettings;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.model.opening.KeydateSort;
import seedu.ultron.model.opening.Opening;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Opening> PREDICATE_SHOW_ALL_OPENINGS = unused -> true;

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
    Path getUltronFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setUltronFilePath(Path ultronFilePath);

    /**
     * Replaces Ultron data with the data in {@code ultron}.
     */
    void setUltron(ReadOnlyUltron ultron);

    /** Returns the Ultron */
    ReadOnlyUltron getUltron();

    /**
     * Returns true if a opening with the same identity as {@code opening} exists in the address book.
     */
    boolean hasOpening(Opening opening);

    /**
     * Deletes the given opening.
     * The opening must exist in the address book.
     */
    void deleteOpening(Opening target);

    /**
     * Adds the given opening.
     * {@code opening} must not already exist in the address book.
     */
    void addOpening(Opening opening);

    /**
     * Replaces the given opening {@code target} with {@code editedOpening}.
     * {@code target} must exist in the address book.
     * The opening identity of {@code editedOpening} must not be the same
     * as another existing opening in the address book.
     */
    void setOpening(Opening target, Opening editedOpening);

    /** Returns an unmodifiable view of the filtered opening list */
    ObservableList<Opening> getFilteredOpeningList();

    /**
     * Updates the filter of the filtered opening list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOpeningList(Predicate<Opening> predicate);

    /**
     * Sorts the filter of the filtered opening list in the given {@code direction}
     * @param direction
     */
    public void sortFilteredOpeningList(KeydateSort direction);

    /**
     * Returns the currently selected opening in the filtered opening list.
     */
    Opening getSelectedOpening();

    /**
     * Resets the index.
     */
    void resetIndex();

    /**
     * Returns the index of currently selected opening in the filtered opening list.
     */
    Index getSelectedIndex();

    /**
     * Sets the index of currently selected opening in the filtered opening list.
     */
    void setSelectedIndex(Index index);

    boolean hasSelectedIndex();
}
