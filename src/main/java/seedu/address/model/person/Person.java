package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Status status;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private Image image;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Status status, Phone phone, Email email, Address address, Set<Tag> tags, Image image) {
        requireAllNonNull(name, status, phone, email, address, tags, image);
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.image = image;
    }

    /**
     * Overloaded constructor for Person without an image provided.
     * A default image will be used for this person.
     *
     * @param name
     * @param status
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Person(Name name, Status status, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.image = new Image();
    }

    public Name getName() {
        return name;
    }

    public Status getStatus() {
        return status;
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Image getImage() {
        return image;
    }

    public String getImagePath() {
        return this.image.getFullString();
    }

    public static String getDefaultImagePath() {
        return Image.DEFAULT_IMAGE_PATH;
    }

    public boolean hasDefaultImage() {
        return this.image.isDefaultImage();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
            && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
            && otherPerson.getStatus().equals(getStatus())
            && otherPerson.getPhone().equals(getPhone())
            && otherPerson.getEmail().equals(getEmail())
            && otherPerson.getAddress().equals(getAddress())
            && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, status, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Status: ")
            .append(getStatus())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail())
            .append("; Address: ")
            .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
