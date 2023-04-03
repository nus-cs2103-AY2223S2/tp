package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Session> PREDICATE_SHOW_ALL_SESSIONS = unused -> true;

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

    /** Returns an unmodifiable view of the filtered session list */
    ObservableList<Session> getFilteredSessionList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Sorts the person list with specified attribute.
     */
    void sortAddressBook(int targetField);

    void setSession(Session target, Session editedSession);

    /**
     * Commits the current state of the address book.
     * Removes all elements of the addressBookStateList beyond the current pointer.
     */
    void commitAddressBook();

    /**
     * Undoes the previous command by resetting the state of the address book to the previous state.
     * @throws CommandException If there are no more commands left to undo.
     */
    void undoAddressBook() throws CommandException;

    /**
     * Redoes the previous undone command by resetting the state of the address book to the next state.
     * @throws CommandException If there are no more commands left to redo.
     */
    void redoAddressBook() throws CommandException;

    ObservableList<CalendarEvent> getFilteredCalendarEventList();

    void updateFilteredSessionList(Predicate<Session> predicate);

    void updateCalendarEventList();

    boolean hasSession(Session toAdd);

    void addSession(Session toAdd);

    void removeSession(Session toRemove);

    void addPersonToSession(Person person, Session session);

    void removePersonFromSession(Person person, Session session);

    Session getSessionFromName(SessionName name);

    boolean hasSessionName(SessionName sessionName);
}
