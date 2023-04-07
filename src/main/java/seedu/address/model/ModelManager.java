package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.time.TimeMask;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Group> filteredGroups;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredGroups = new FilteredList<>(this.addressBook.getGroupList());
        ObservableList<String> emptyList = FXCollections.observableArrayList();
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

    public void deleteExpiredEvent() {
        addressBook.deleteExpiredEvent();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addRecurringEvent(Person person, RecurringEvent event) {
        requireAllNonNull(person, event);
        addressBook.addRecurringEvent(person, event);
    }

    @Override
    public void setRecurringEvent(Person person, RecurringEvent originalEvent, RecurringEvent editedRecurringEvent) {
        requireAllNonNull(person, originalEvent, editedRecurringEvent);
        addressBook.setRecurringEvent(person, originalEvent, editedRecurringEvent);
    }

    @Override
    public void deleteRecurringEvent(Person person, RecurringEvent event) {
        requireAllNonNull(person, event);
        addressBook.deleteRecurringEvent(person, event);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addPersonInGroup(Person person, Group group) {
        requireAllNonNull(person, group);
        addressBook.addPersonInGroup(person, group);
    }

    @Override
    public void removePersonFromGroup(Person person, Group group) {
        requireAllNonNull(person, group);
        addressBook.removePersonFromGroup(person, group);
    }

    @Override
    public void addGroup(Group group) {
        requireNonNull(group);
        addressBook.addGroup(group);
    }

    @Override
    public void deleteGroup(Group group) {
        requireNonNull(group);
        addressBook.deleteGroup(group);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return addressBook.hasGroup(group);
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

    //=========== Filtered Group List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTimeSlotList(Group group, LocalDate date) {
        requireAllNonNull(group, date);

        // TODO: Refactor
        List<Person> persons = this.addressBook.getPersonList();
        TimeMask baseMask = new TimeMask();
        for (Person person: persons) {
            if (person.getGroups().contains(group)) {
                baseMask.mergeMask(person.getRecurringMask());
                IsolatedEventList isolatedEventList = person.getIsolatedEventList();
                if (isolatedEventList == null) {
                    continue;
                }
                baseMask.mergeIsolatedEvents(isolatedEventList, date);
            }
        }

        // TODO: Potential bugs
        ArrayList<ArrayList<Integer>> timetable = TimeMask.getTimeSlotIndexes(baseMask);
        addressBook.getScheduleWeek().setInternalList(timetable, date.getDayOfWeek());
        // TODO: Consider removing
        logger.info("Timetable generation finished. Rendering expected.");
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
