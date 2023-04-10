package seedu.event.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.event.commons.core.GuiSettings;
import seedu.event.model.contact.Contact;
import seedu.event.model.event.Event;
import seedu.event.model.event.Rate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;

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
     * Returns the user prefs' event book file path.
     */
    Path getEventBookFilePath();

    Path getContactListFilePath();

    /**
     * Sets the user prefs' event book file path.
     */
    void setEventBookFilePath(Path eventBookFilePath);

    void setContactListFilePath(Path contactListFilePath);

    /**
     * Replaces event book data with the data in {@code eventBook}.
     */
    void setEventBook(ReadOnlyEventBook eventBook);

    /**
     * Replaces contact list data with the data in {@code contactList}.
     */
    void setContactList(ReadOnlyContactList contactList);

    /** Returns the EventBook */
    ReadOnlyEventBook getEventBook();

    /** Returns the ContactList */
    ReadOnlyContactList getContactList();


    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contact list.
     */
    boolean hasContact(Contact contact);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the event book.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the event book.
     */
    void deleteEvent(Event target);

    /**
     * Marks the given event.
     * The event must exist in the event book.
     */
    void markEvent(Event target);

    /**
     * Unmarks the given event.
     * The event must exist in the event book.
     */
    void unmarkEvent(Event target);


    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the contact list.
     */
    void addContact(Contact contact);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the event book.
     */
    void addEvent(Event event);

    /**
     * Retrieves the rate of an event.
     */
    Rate getRate(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the event book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the event book.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();


    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    void updateFilteredContactList(Predicate<Contact> predicate);

    void linkContact(Event event, Event linkedEvent);
}
