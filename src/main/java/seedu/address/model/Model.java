package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.ui.tab.TabInfo;
import seedu.address.logic.ui.tab.TabUtil;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;

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
     * Gets the given person with name of {@code name}.
     * Person with name of {@code target} must exist in the address book.
     */
    Person getPersonWithName(String name);

    /**
     * Checks if the Person with name of {@code name} exists in the address book.
     */
    boolean hasPersonWithName(String name);

    /** Checks if {@code p} is a part of the taggedPerson set of event at {@code index}. */
    boolean isPersonTaggedToEvent(Index index, Person p);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getUserDataFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setUserDataFilePath(Path userDataFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setUserData(ReadOnlyUserData userData);

    /** Returns the UserData */
    ReadOnlyUserData getUserData();

    /** Sets the user in UserData */
    void setUser(User user);

    /** Checks if {@code event} exists in UserData */
    boolean hasEvent(Event event);

    /** Adds {@code event} to the event list */
    void addEvent(Event event);

    /** Deletes {@code event} to the event list */
    void deleteEvent(Event event);

    /** Gets list of all events */
    ObservableList<Event> getEvents();

    /** Gets the event at {@code index}. */
    Event getEvent(Index index);

    /** Sets the {@code oldEvent} to {@code newEvent}. */
    void setEvent(Event oldEvent, Event newEvent);

    /** Tags {@code taggingPerson} to {@code Event}. */
    void tagPersonToEvent(Index eventIndex, Person taggingPerson);

    /** Untags {@code taggingPerson} from {@code Event}. */
    void untagPersonToEvent(Index eventIndex, Person taggingPerson);

    /** Edits person for all events that contain {@code personToEdit} to {@code editedPerson}. */
    void editPersonForAllEvents(Person personToEdit, Person editedPerson);

    /** Checks if {@code index} is a valid tab index. */
    boolean isValidTabIndex(Index index);

    /** Gets the TabUtil object */
    TabUtil getTabUtil();

    /** Gets the selected tab */
    ReadOnlyObjectProperty<TabInfo> getSelectedTab();

    /** Sets the tab to the one specified in {@code index} */
    void setSelectedTab(Index index);

    /** Gets the selected {@code Person} */
    ReadOnlyObjectProperty<Person> getSelectedPerson();

    /** Sets the selected person to {@code person} */
    void setSelectedPerson(Person person);

}
