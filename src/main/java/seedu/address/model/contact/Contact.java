package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a contact in the internship application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final Phone phone;
    private final Email email;

    /**
     * Every field must be present and not null.
     */
    public Contact(Phone phone, Email email) {
        requireAllNonNull(phone, email);
        this.phone = phone;
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns true if both contacts have the same identity.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return otherContact.getPhone().equals(getPhone())
                && otherContact.getEmail().equals(getEmail());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(phone, email);
    }

    @Override
    public String toString() {
        String builder = "Phone: "
                + getPhone()
                + "; Email: "
                + getEmail();

        return builder;
    }

}
