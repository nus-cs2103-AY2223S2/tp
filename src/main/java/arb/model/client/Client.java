package arb.model.client;

import static arb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import arb.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: name and tags are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Name and tags must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.phone = Optional.ofNullable(phone);
        this.email = Optional.ofNullable(email);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return this.name;
    }

    /**
     * Returns true if this client has a phone.
     */
    public boolean isPhonePresent() {
        return this.phone.isPresent();
    }

    public Phone getPhone() {
        return this.phone.orElse(null);
    }

    /**
     * Returns true if this client has an email.
     */
    public boolean isEmailPresent() {
        return this.email.isPresent();
    }

    public Email getEmail() {
        return this.email.orElse(null);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;

        boolean isNameEqual = otherClient.getName().equals(getName());
        boolean isTagsEqual = otherClient.getTags().equals(getTags());

        boolean isPhoneEqual;

        if (!isPhonePresent()) {
            isPhoneEqual = otherClient.getPhone() == null;
        } else {
            isPhoneEqual = getPhone().equals(otherClient.getPhone());
        }

        boolean isEmailEqual;

        if (!isEmailPresent()) {
            isEmailEqual = otherClient.getEmail() == null;
        } else {
            isEmailEqual = getEmail().equals(otherClient.getEmail());
        }

        return isNameEqual && isTagsEqual && isPhoneEqual && isEmailEqual;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        if (isPhonePresent()) {
            builder.append("; Phone: ").append(getPhone());
        }

        if (isEmailPresent()) {
            builder.append("; Email: ").append(getEmail());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
