package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

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


    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Adds the given person into a group.
     * {@code person} must not already exist in the {@code group}
     */
    void addPersonInGroup(Person person, Group group);

    /**
     * Removes the given person from a group.
     * {@code person} must exist in the {@code group}
     */
    void removePersonFromGroup(Person person, Group group);

    /**
     * Adds a new group
     * {@code group} must not exist in the address book
     */
    void addGroup(Group group);

    /**
     * Deletes a new group and removes group from every person in it
     * {@code group} must exist in the address book
     */
    void deleteGroup(Group group);

    /**
     * Returns true if a group with the same group name as {@code group} exists in the address book.
     */
    boolean hasGroup(Group group);

    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredGroupList(Predicate<Group> predicate);

    // TODO: Change type
    void updateFilteredTimeSlotList(Group group, LocalDate date);

    /**
     * Add Recurring Event object to the person's isolated event list
     * @param personToEdit
     * @param eventToAdd
     */
    void addRecurringEvent(Person personToEdit, RecurringEvent eventToAdd);

    void deleteRecurringEvent(Person personToEdit, RecurringEvent event);

    void setRecurringEvent(Person personToEdit, RecurringEvent originalEvent, RecurringEvent editedRecurringEvent);

    void deleteExpiredEvent();
}
