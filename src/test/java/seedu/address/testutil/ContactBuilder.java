package seedu.address.testutil;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;

/**
 * A utility class to help with building ContactList objects.
 * Example usage: <br>
 *     {@code ContactBook cb = new ContactListBuilder().withContact("John", "Doe").build();}
 */
public class ContactBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";

    private ContactName name;
    private ContactPhone phone;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new ContactName(DEFAULT_NAME);
        phone = new ContactPhone(DEFAULT_PHONE);
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
    }

    /**
     * Sets the {@code ContactName} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new ContactName(name);
        return this;
    }


    /**
     * Sets the {@code ContactPhone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new ContactPhone(phone);
        return this;
    }

    public Contact build() {
        return new Contact(name, phone);
    }

}
