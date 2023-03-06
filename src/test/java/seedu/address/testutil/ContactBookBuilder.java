package seedu.address.testutil;

import seedu.address.model.ContactBook;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building Contactbook objects.
 * Example usage: <br>
 *  {@code ContactBook cb = new ContactBookBuilder().withContact("John", "Doe").build();}
 */
public class ContactBookBuilder {

    private ContactBook contactBook;

    public ContactBookBuilder() {
        contactBook = new ContactBook();
    }

    public ContactBookBuilder(ContactBook contactBook) {
        this.contactBook = contactBook;
    }

    /**
     * Adds a new {@code Contact} to the {@code ContactBook} that we are building.
     */
    public ContactBookBuilder withContact(Contact contact) {
        contactBook.addContact(contact);
        return this;
    }
}
