package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a contact.
 */
public class Contact {

    private ContactName name;
    private ContactPhone phone;

    /**
     * Every field must be present and not null.
     */
    public Contact(ContactName name, ContactPhone phone) {
        requireAllNonNull(name, phone);
        this.name = name;
        this.phone = phone;
    }

    public Contact() {};

    public ContactName getName() {
        return name;
    }

    public ContactPhone getPhone() {
        return phone;
    }

    /**
     * Returns true if both contacts have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == null) {
            return false;
        }

        if (otherContact == this) {
            return true;
        }

        if (this.name == otherContact.name) {
            return true;
        }

        if (otherContact.getName().equals(this.getName())) {
            return true;
        }

        return false;
    }

    public boolean isNull() {
        return this.name == null;
    }


    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        if (this.name == otherContact.name && this.phone == otherContact.phone) {
            return true;
        }

        return otherContact.getName().equals(getName())
                && otherContact.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());

        return builder.toString();
    }

    /**
     * Formats the string needed to save in Json file
     */
    public String toCardString() {
        try {
            return name.fullName + " HP:" + phone.value;
        } catch (NullPointerException npe) {
            return " ";
        }
    }

}
