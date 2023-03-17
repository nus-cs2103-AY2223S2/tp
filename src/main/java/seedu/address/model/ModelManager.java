package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.event.Event;
import seedu.address.model.event.Rate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EventBook eventBook;
    private final ContactList contactList;
    private final UserPrefs userPrefs;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Contact> filteredContacts;

    /**
     * Initializes a ModelManager with the given eventBook and userPrefs.
     */
    public ModelManager(ReadOnlyEventBook addressBook, ReadOnlyContactList contactList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, contactList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " with contact list: " + contactList
                + " and user prefs " + userPrefs);

        this.eventBook = new EventBook(addressBook);
        this.contactList = new ContactList(contactList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEvents = new FilteredList<>(this.eventBook.getEventList());
        filteredContacts = new FilteredList<>(this.contactList.getContactList());

    }

    public ModelManager() {
        this(new EventBook(), new ContactList(), new UserPrefs());
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
    public Path getEventBookFilePath() {
        return userPrefs.getEventBookFilePath();
    }

    @Override
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void setEventBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setEventBookFilePath(addressBookFilePath);
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        userPrefs.setContactListFilePath(contactListFilePath);
    }

    //=========== EventBook ================================================================================

    @Override
    public void setEventBook(ReadOnlyEventBook addressBook) {
        this.eventBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return eventBook;
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return eventBook.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        eventBook.removeEvent(target);
    }

    @Override
    public void markEvent(Event target) {
        requireNonNull(target);
        eventBook.markEvent(target);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void unmarkEvent(Event target) {
        requireNonNull(target);
        eventBook.unmarkEvent(target);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void addEvent(Event event) {
        eventBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        eventBook.setEvent(target, editedEvent);
    }

    //=========== Filtered Event List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return contactList;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contactList.hasContact(contact);
    }

    @Override
    public void addContact(Contact contact) {
        contactList.addContact(contact);
    }

    @Override
    public void linkContact(Event event, Event linkedEvent) {
        eventBook.linkContact(event, linkedEvent);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public Rate getRate(Event event) {
        return event.getRate();
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
        return eventBook.equals(other.eventBook)
                && contactList.equals(other.contactList)
                && userPrefs.equals(other.userPrefs)
                && filteredEvents.equals(other.filteredEvents);
    }

}
