package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final FullNamePredicate DEFAULT_EMPTY_NAME_PREDICATE = new FullNamePredicate("");

    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> targetPerson;
    private final VersionedAddressBook versionedAddressBook;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        targetPerson = new FilteredList<Person>(versionedAddressBook.getPersonList());
        setDefaultShowPerson();
        filteredPersons = new FilteredList<>(versionedAddressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        versionedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public boolean canReplacePerson(Person toBeReplaced, Person replacement) {
        requireAllNonNull(toBeReplaced, replacement);
        return versionedAddressBook.canReplacePerson(toBeReplaced, replacement);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        versionedAddressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean checkUndoable() {
        return versionedAddressBook.checkUndoable();
    }

    @Override
    public boolean checkRedoable() {
        return versionedAddressBook.checkRedoable();
    }

    @Override
    public String undoAddressBook() {
        return versionedAddressBook.undo();
    }

    @Override
    public String redoAddressBook() {
        return versionedAddressBook.redo();
    }

    @Override
    public void commitAddressBook(String lastExecutedCommand) {
        versionedAddressBook.commit(lastExecutedCommand);
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
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void setDefaultShowPerson() {
        targetPerson.setPredicate(DEFAULT_EMPTY_NAME_PREDICATE);
    }

    @Override
    public void updateShowPerson(Predicate<Person> predicate) {
        requireNonNull(predicate);
        targetPerson.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getShowPerson() {
        return targetPerson;
    }

    @Override
    public ArrayList<String> getExistingTagValues() {
        return versionedAddressBook.getExistingTagValues();
    }

    @Override
    public ArrayList<String> getExistingModuleValues() {
        return versionedAddressBook.getExistingModuleValues();
    }

    @Override
    public ArrayList<String> getExistingEducationValues() {
        return versionedAddressBook.getExistingEducationValues();
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
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
