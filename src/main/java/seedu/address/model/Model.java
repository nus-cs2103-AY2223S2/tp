package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person in the address book can be replaced by another
     * without causing any duplicates of person in the address book.
     *
     * @param toBeReplaced The person to be replaced.
     * @param replacement The replacement person.
     */
    boolean canReplacePerson(Person toBeReplaced, Person replacement);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the person to be shown as an empty person.
     */
    void setDefaultShowPerson();

    /**
     * Updates the person to be shown as the person who matches the predicate.
     * @param predicate Full name of person to be shown.
     */
    void updateShowPerson(Predicate<Person> predicate);

    /**
     * Returns the person to be shown in an ObservableList.
     * @return ObservableList containing the person be shown.
     */
    ObservableList<Person> getShowPerson();

    /**
     * Checks if the versionedAddressBook has prior history to be undone.
     * @return true if undo is possible and false otherwise.
     */
    boolean checkUndoable();

    /**
     * Checks if the versionedAddressBook has recent undo history to be redone.
     * @return true if redo is possible and false otherwise.
     */
    boolean checkRedoable();

    /**
     * Restores a previous version of AddressBook, and returns the name of the
     * command that was undone.
     *
     * @return The name of the command that was undone.
     */
    String undoAddressBook();

    /**
     * Restores an undone version of AddressBook, and returns the name of the command
     * that was redone.
     *
     * @return The name of the command that was redone.
     */
    String redoAddressBook();

    /**
     * Saves the current version of AddressBook and the last executed command.
     *
     * @param lastExecutedCommand The name of the command to be updated to the command history.
     */
    void commitAddressBook(String lastExecutedCommand);

    /**
     * Returns a list of all existing tag values (without duplicates) contained
     * in the person list.
     */
    ArrayList<String> getExistingTagValues();

    /**
     * Returns a list of all existing module values (without duplicates) contained
     * in the person list.
     */
    ArrayList<String> getExistingModuleValues();

    /**
     * Returns a list of all existing education values (without duplicates) contained
     * in the person list.
     */
    ArrayList<String> getExistingEducationValues();
}
