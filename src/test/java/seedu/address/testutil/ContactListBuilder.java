package seedu.address.testutil;

import seedu.address.model.ContactList;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building ContactList objects.
 * Example usage: <br>
 *  {@code ContactBook cb = new ContactListBuilder().withContact("John", "Doe").build();}
 */
public class ContactListBuilder {

    private ContactList contactList;

    public ContactListBuilder() {
        contactList = new ContactList();
    }

    public ContactListBuilder(ContactList contactList) {
        this.contactList = contactList;
    }

    /**
     * Adds a new {@code Contact} to the {@code ContactBook} that we are building.
     */
    public ContactListBuilder withContact(Contact contact) {
        contactList.addContact(contact);
        return this;
    }

    public ContactList build() {
        return contactList;
    }
}
