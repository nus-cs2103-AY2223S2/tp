package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Event;
import seedu.address.model.person.Rate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    Path getContactListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    void setContactListFilePath(Path contactListFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the ContactList */
    ReadOnlyContactList getContactList();


    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contact list.
     */
    boolean hasContact(Contact contact);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Event person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Event target);

    /**
     * Marks the given event.
     * The event must exist in the address book.
     */
    void markEvent(Event target);

    /**
     * Unmarks the given event.
     * The event must exist in the address book.
     */
    void unmarkEvent(Event target);


    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the contact list.
     */
    void addContact(Contact contact);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Event person);

    /**
     * Retrieves the rate of an event.
     */
    Rate getRate(Event event);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Event target, Event editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Event> getFilteredPersonList();


    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Event> predicate);

    void updateFilteredContactList(Predicate<Contact> contact);

    void linkContact(Event event, Event linkedEvent);
}
