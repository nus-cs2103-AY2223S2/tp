package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.CannotRedoAddressBookException;
import seedu.address.commons.exceptions.CannotUndoAddressBookException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.backup.Backup;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.storage.BackupDataStorage;
import seedu.address.storage.JsonAdaptedBackup;
import seedu.address.storage.JsonBackupDataStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Path userPrefsPath = Path.of("preferences.json");
    private final Path backupDataPath = Path.of("data/backup/backupData.json");

    private final VersionedAddressBook addressBook;
    private final UserPrefs userPrefs;
    private final BackupData backupData;
    private final FilteredList<Person> filteredPersons;
    private final UserPrefsStorage userPrefsStorage;
    private final BackupDataStorage backupDataStorage;

    /**
     * Initializes a ModelManager with the given addressBook userPrefs, and backupData
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyBackupData backupData) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.backupData = new BackupData(backupData);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.userPrefsStorage = new JsonUserPrefsStorage(userPrefsPath);
        this.backupDataStorage = new JsonBackupDataStorage(backupDataPath);
    }

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.backupData = new BackupData();
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.userPrefsStorage = new JsonUserPrefsStorage(userPrefsPath);
        this.backupDataStorage = new JsonBackupDataStorage(backupDataPath);
    }

    public ModelManager() {
        this(new VersionedAddressBook(new AddressBook()), new UserPrefs(), new BackupData());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public UserPrefsStorage getUserPrefsStorage() {
        return userPrefsStorage;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== BackupData ================================================================================

    @Override
    public void setBackupData(BackupData newBackupData) {
        backupData.resetData(newBackupData);
    }

    @Override
    public void addBackupToBackupData(Backup backup) {
        JsonAdaptedBackup jsonBackup = new JsonAdaptedBackup(backup);
        backupData.addBackup(jsonBackup);
    }

    @Override
    public void removeBackupFromBackupData(String index) throws IndexOutOfBoundsException {
        JsonAdaptedBackup jsonBackup = backupData.getBackup(index);
        backupData.deleteBackup(jsonBackup);
    }

    @Override
    public BackupData getBackupData() {
        return this.backupData;
    }

    @Override
    public ObservableList<Backup> getBackupList() throws IllegalValueException {
        ObservableList<Backup> observableBackups = FXCollections.observableArrayList();
        List<Backup> backups = backupData.getRawBackups();
        observableBackups.addAll(backups);
        return observableBackups;
    }

    @Override
    public BackupDataStorage getBackupDataStorage() {
        return backupDataStorage;
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        commitAddressBook();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        commitAddressBook();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        commitAddressBook();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        commitAddressBook();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        if (predicate == null) {
            filteredPersons.setPredicate(null);
        } else {
            filteredPersons.setPredicate(predicate);
        }
    }

    @Override
    public void undoAddressBook() {
        try {
            addressBook.undo();
        } catch (CannotUndoAddressBookException e) {
            logger.warning("No undoable state found.");
        }
    }

    @Override
    public void redoAddressBook() {
        try {
            addressBook.redo();
        } catch (CannotRedoAddressBookException e) {
            logger.warning("No redoable state found.");
        }
    }

    @Override
    public boolean canUndoAddressBook() {
        return addressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return addressBook.canRedo();
    }

    @Override
    public void commitAddressBook() {
        addressBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
            && userPrefs.equals(other.userPrefs)
            && filteredPersons.equals(other.filteredPersons);
    }

    @Override
    public Person findPersonByNric(Nric nric) {
        return addressBook.findPersonByNric(nric);
    }
}
