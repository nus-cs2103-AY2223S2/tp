package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.ui.tab.TabInfo;
import seedu.address.logic.ui.tab.TabType;
import seedu.address.logic.ui.tab.TabUtil;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final UserData userData;
    private final FilteredList<Person> filteredPersons;
    private final TabUtil tabUtil;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyUserData userData) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.userData = new UserData(userData);
        filteredPersons = new FilteredList<>(this.addressBook.getData());
        this.tabUtil = new TabUtil(TabType.getAll());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new UserData());
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
    public Path getUserDataFilePath() {
        return this.userPrefs.getUserDataFilePath();
    }

    @Override
    public void setUserDataFilePath(Path userDataFilePath) {
        requireNonNull(userDataFilePath);
        this.userPrefs.setUserDataFilePath(userDataFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
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
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== UserData ================================================================================

    @Override
    public void setUserData(ReadOnlyUserData userData) {
        this.userData.resetData(userData);
    }

    @Override
    public ReadOnlyUserData getUserData() {
        return this.userData;
    }

    @Override
    public void setUser(User user) {
        this.userData.setUser(user);
    }

    @Override
    public boolean hasEvent(Event event) {
        return this.userData.hasEvent(event);
    }

    @Override
    public void addEvent(Event event) {
        this.userData.addEvent(event);
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

    //=========== Tabs =======================================================================================


    @Override
    public boolean isValidTabIndex(Index index) {
        return tabUtil.isIndexInRange(index);
    }

    @Override
    public TabUtil getTabUtil() {
        return tabUtil;
    }

    @Override
    public ReadOnlyObjectProperty<TabInfo> getSelectedTab() {
        return tabUtil.getSelectedTab();
    }

    @Override
    public void setSelectedTab(Index index) {
        tabUtil.setSelectedTab(index);
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
                && filteredPersons.equals(other.filteredPersons)
                && tabUtil.equals(other.tabUtil);
    }
}
