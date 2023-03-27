package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.backup.Backup;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.storage.BackupDataStorage;
import seedu.address.storage.UserPrefsStorage;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
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
     * Returns the user prefs storage.
     */
    UserPrefsStorage getUserPrefsStorage();

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
     * Sets the backup data
     */
    void setBackupData(BackupData backupData);

    /**
     * Adds a backup to the backup data
     */
    void addBackupToBackupData(Backup backup);

    /**
     * Removes a backup from BackupData
     */

    void removeBackupFromBackupData(String index) throws IndexOutOfBoundsException;

    BackupData getBackupData();

    BackupDataStorage getBackupDataStorage();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

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

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Restores the address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Returns true if the model has previous address states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    Person findPersonByNric(Nric nric);

    /**
     * Returns an unmodifiable view of the backup list
     */
    ObservableList<Backup> getBackupList() throws IllegalValueException;
}
