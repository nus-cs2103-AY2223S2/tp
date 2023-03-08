package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;

/**
 * Unmodifiable view of a contact list
 */
public interface ReadOnlyContactList {
    /**
     * Returns an unmodifiable view of the contact list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getContactList();
}
