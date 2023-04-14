package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_FAVORITED = person -> person.getIsFavorite();

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

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
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if one of the persons given has the same identity as a {@code persons} that exists in the address
     * book.
     */
    boolean hasPersons(List<Person> persons);

    /**
     * Returns the index of the first duplicate found between the given {@code person}s and the address book.
     * Returns -1 if no duplicates are found.
     */
    int getDuplicateIndex(List<Person> persons);

    /**
     * Returns the {@code String} representation of the duplicate field found between the given {@code duplicatePerson}
     * and the address book.
     * Returns empty {@code String} if no duplicates are found.
     */
    String getDuplicateString(Person duplicatePerson);

    /**
     * Returns the {@code String} representation of the duplicate field found between the given
     * {@code duplicateEditedPerson} and the entire address book, not considering any duplicates found between the
     * {@code duplicateEditedPerson} and the {@code notCounted} entry.
     * Returns empty {@code String} if no duplicates are found.
     */
    String getDuplicateStringForEdit(Person duplicateEditedPerson, Person notCounted);

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
     * Adds the given persons.
     * All the elements in {@code persons} must not already exist in the address book.
     */
    void addPersons(List<Person> persons);

    /**
     * Checks if the given {@code editedPerson} is a valid Person to replace the {@code target}, and that it is not a
     * duplicate of another existing person in the address book.
     */
    boolean canEdit(Person target, Person editedPerson);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be a duplicate of another existing person in the address
     * book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Saves the current address book state to history.
     *
     * @param currentState The current state of the address book.
     */
    void commit(ReadOnlyAddressBook currentState);

    /**
     * Restores the previous address book state from history.
     */
    void undo();

    /**
     * Checks whether there are old address book states in history to undo.
     *
     * @return A boolean indicating if there are old address book states in history.
     */
    boolean canUndo();

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the favorited person list
     */
    ObservableList<Person> getFavoritedPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

}
