package seedu.event.model;

import javafx.collections.ObservableList;
import seedu.event.model.contact.Contact;

/**
 * Unmodifiable view of a contact list
 */
public interface ReadOnlyContactList {
    /**
     * Returns an unmodifiable view of the contact list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Contact> getContactList();
}
