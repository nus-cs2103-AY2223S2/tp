package seedu.address.model.pet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Pet in the store.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Pet {

    // Identity fields
    private final OwnerName ownerName;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private boolean isMarked;

    // Data fields
    private final Address address;
    private final Deadline deadline;
    private final Set<Tag> tags = new HashSet<>();
    private final LocalDateTime timestamp;
    private final Cost cost;

    /**
     * Every field must be present and not null.
     */
    public Pet(OwnerName ownerName, Name name, Phone phone, Email email, Address address,
               LocalDateTime timestamp, Deadline deadline, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, timestamp, deadline, tags);
        this.ownerName = ownerName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timestamp = timestamp;
        this.cost = new Cost(timestamp);
        this.deadline = deadline;
        this.tags.addAll(tags);
        this.isMarked = false;
    }
    public OwnerName getOwnerName() {
        return ownerName;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public Cost getCost() {
        return cost;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both pets have the same name.
     * This defines a weaker notion of equality between two pets.
     */
    public boolean isSamePet(Pet otherPet) {
        if (otherPet == this) {
            return true;
        }

        return otherPet != null
                && otherPet.getName().equals(getName());
    }

    /**
     * Returns true if both pets have the same identity and data fields.
     * This defines a stronger notion of equality between two pets.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pet)) {
            return false;
        }

        Pet otherPet = (Pet) other;
        return otherPet.getOwnerName().equals(getOwnerName())
                && otherPet.getName().equals(getName())
                && otherPet.getPhone().equals(getPhone())
                && otherPet.getEmail().equals(getEmail())
                && otherPet.getAddress().equals(getAddress())
                && otherPet.getTimeStamp().equals(getTimeStamp())
                && otherPet.getDeadline().equals(getDeadline())
                && otherPet.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(ownerName, name, phone, email, address, cost, deadline, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getOwnerName())
                .append("; Name: ")
                .append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Cost: ")
                .append(getCost())
                .append("; Deadline: ")
                .append(getDeadline());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}
