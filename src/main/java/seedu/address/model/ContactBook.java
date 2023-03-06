package seedu.address.model;


import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;


/**
 * Wraps all data at the contact-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ContactBook implements ReadOnlyContactBook {

    public final UniqueContactList contacts;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        contacts = new UniqueContactList();
    }

    public ContactBook() {}

    /**
     * Creates an ContactBook using the Contacts in the {@code toBeCopied}
     */
    public ContactBook(ReadOnlyContactBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the contact list with {@code contacts}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Contact> contacts) {
        this.contacts.setContacts(contacts);
    }

    /**
     * Resets the existing data of this {@code ContactBook} with {@code newData}.
     */
    public void resetData(ReadOnlyContactBook newData) {
        requireNonNull(newData);

        setPersons(newData.getContactList());
    }

    //// contact-level operations

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the contact book.
     */
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return contacts.contains(contact);
    }

    /**
     * Adds a contact to the contact book.
     * The contact must not already exist in the contact book.
     */
    public void addContact(Contact p) {
        contacts.add(p);
    }

    /**
     * Replaces the given contact {@code target} in the list with {@code editedContact}.
     * {@code target} must exist in the contact book.
     * The contact identity of {@code editedContact} must not be the same as another existing
     * contact in the contact book.
     */
    public void setContact(Contact target, Contact editedContact) {
        requireNonNull(editedContact);

        contacts.setContact(target, editedContact);
    }

    /**
     * Removes {@code key} from this {@code ContactBook}.
     * {@code key} must exist in the contact book.
     */
    public void removeContact(Contact key) {
        contacts.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return contacts.asUnmodifiableObservableList().size() + " contacts";
        // TODO: refine later
    }

    @Override
    public ObservableList<Contact> getContactList() {
        return contacts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactBook // instanceof handles nulls
                && contacts.equals(((ContactBook) other).contacts));
    }

    @Override
    public int hashCode() {
        return contacts.hashCode();
    }
}
