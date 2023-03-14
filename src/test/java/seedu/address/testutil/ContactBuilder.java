package seedu.address.testutil;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;

/**
 * A utility class to help with building Contact objects.
 */
public class ContactBuilder {

    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "companya@gmail.com";

    private Phone phone;
    private Email email;
    private Contact contact;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new seedu.address.model.contact.Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new seedu.address.model.contact.Email(email);
        return this;
    }

    public Contact build() {
        return new Contact(phone, email);
    }
}
