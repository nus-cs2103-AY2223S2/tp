package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model, Undoable {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final UndoManager undoManager;
    private final UserData userData;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyUserData userData) {
        requireAllNonNull(addressBook, userPrefs, userData);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.undoManager = new UndoManager(this.addressBook, 5);
        this.userData = new UserData(userData);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new UserData());
    }

    //=========== UserData ==================================================================================
    public void setHashedPassword(String hashedPassword) {
        this.userData.setHashedPassword(hashedPassword);
    }

    public String getHashedPassword() {
        return this.userData.getHashedPassword();
    }

    public void setNumberOfTimesUsed(int numberOfTimesUsed) {
        this.userData.setNumberOfTimesUsed(numberOfTimesUsed);
    }

    public int getNumberOfTimesUsed() {
        return this.userData.getNumberOfTimesUsed();
    }

    public ReadOnlyUserData getUserData() {
        return this.userData;
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

    @Override
    public String getCssFilePath() {
        return userPrefs.getCssFilePath();
    }

    @Override
    public void setCssFilePath(String cssFilePath) {
        requireNonNull(cssFilePath);
        userPrefs.setCssFilePath(cssFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        //setAddressBook is currently only used to clear the addressBook, so this method is temporarily set to
        // show clear. Note that in future implementations if setAddressBook is used for other purposes, this method
        // will need to be edited.
        undoManager.addToHistory(this.addressBook, "Clear");
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
        undoManager.addToHistory(this.addressBook, String.format("Delete %1$s", target));
    }

    @Override
    public void deleteMultiplePersons(List<Person> list) {
        for (Person target: list) {
            addressBook.removePerson(target);
        }
        undoManager.addToHistory(this.addressBook, "Deleted list of people");
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        undoManager.addToHistory(this.addressBook, String.format("Add %1$s", person));
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        undoManager.addToHistory(this.addressBook, String.format("Edit %1$s", editedPerson));
    }

    @Override
    public void combine(ReadOnlyAddressBook toBeCombined, String path) {
        AddressBook newAddressBook = new AddressBook(toBeCombined);
        for (Person p : newAddressBook.getPersonList()) {
            if (addressBook.hasPerson(p)) {
                //Future improvements can be made to specify whether to overwrite or keep persons with the same name
                continue;
            } else {
                addressBook.addPerson(p);
            }
        }
        undoManager.addToHistory(this.addressBook, String.format("Load contents of %s", path));
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

    /**
     * resets each person contact status to be hidden.
     */
    @Override
    public void resetPersonHiddenStatus() {
        List<Person> personlist = getFilteredPersonList();
        personlist.stream().forEach(x -> {
            if (!x.getHidden()) {
                x.toggleHidden();
            }
        });
    }

    /**
     * Sets each person's contact in the person list to be visible.
     * @param personList list of person.
     */
    @Override
    public void showPersonContact(List<Person> personList) {
        personList.stream().forEach(x -> x.toggleHidden());
    }

    //=========== Undo management =============================================================================
    public boolean hasUndoableCommand() {
        return undoManager.hasUndoableCommand();
    }

    /**
     * Undoes the changes made by the last modification command used.
     * @return The string representation of the last modification command used.
     */
    public String executeUndo() {
        Pair<AddressBook, String> previousHistory = undoManager.getPreviousHistory();
        this.addressBook.resetData(previousHistory.getKey());
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return previousHistory.getValue();
    }
    public boolean hasRedoableCommand() {
        return undoManager.hasRedoableCommand();
    }

    /**
     * Redoes the changes unmade by the last undo command
     * @return The string representation of the command redone
     */
    public String executeRedo() {
        Pair<AddressBook, String> nextHistory = undoManager.getNextHistory();
        this.addressBook.resetData(nextHistory.getKey());
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return nextHistory.getValue();
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

}
