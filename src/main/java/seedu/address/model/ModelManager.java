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
import seedu.address.model.person.Event;
import seedu.address.model.person.Rate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final ContactList contactList;
    private final UserPrefs userPrefs;
    private final FilteredList<Event> filteredPersons;
    private final FilteredList<Contact> filteredContacts;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyContactList contactList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, contactList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " with contact list: " + contactList
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.contactList = new ContactList(contactList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredContacts = new FilteredList<>(this.contactList.getContactList());

    }

    public ModelManager() {
        this(new AddressBook(), new ContactList(), new UserPrefs());
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
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        userPrefs.setContactListFilePath(contactListFilePath);
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
    public boolean hasPerson(Event person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Event target) {
        addressBook.removePerson(target);
    }

    @Override
    public void markEvent(Event target) {
        requireNonNull(target);
        addressBook.markEvent(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void unmarkEvent(Event target) {
        requireNonNull(target);
        addressBook.unmarkEvent(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPerson(Event person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Event target, Event editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        addressBook.linkContact(event, linkedEvent);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
        return addressBook.equals(other.addressBook)
                && contactList.equals(other.contactList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
