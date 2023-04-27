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
public class Person implements Comparable<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Photo photo;

    // Data fields
    private final Address address;
    private final Performance performance;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Initiates a null person object without null error.
     */
    public Person() {
        name = new Name();
        phone = new Phone();
        email = new Email();
        photo = new Photo();

        // Data fields
        address = new Address();
        performance = new Performance();
        remark = new Remark();
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Photo photo, Address address,
                  Remark remark, Performance performance, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags, photo, performance);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photo = photo;
        this.address = address;
        this.performance = performance;
        this.remark = remark;
        this.tags.addAll(tags);
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

    public Photo getPhoto() {
        return photo;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Performance getPerformance() {
        return performance;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.equals(this);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     * Two person will not be compared by their photo id as this is a simple mock data
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

        //Nus email must be unique to each student
        if (otherPerson.getEmail().equals(getEmail())) {
            return true;
        }

        //Telegram handle or phone number must be unique to each student
        if (otherPerson.getPhone().equals(getPhone())) {
            return true;
        }



        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getPerformance().equals(getPerformance())
                && otherPerson.getTags().equals(getTags());
    }

    /**
     * Excludes photo since the simple mock data of 15 photos is randomly assigned
     * rather than actually stored in a database / backend
     * @return hashcode
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, performance, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Performance: ")
                .append(getPerformance())
                .append(" Remark: ")
                .append(getRemark());
        return builder.toString();
    }

    @Override
    public int compareTo(Person other) {
        if (other.performance.calculateUrgency() > performance.calculateUrgency()) {
            return 1;
        } else if (other.performance.calculateUrgency() < performance.calculateUrgency()) {
            return -1;
        } else {
            return name.toString().compareTo(other.name.toString());
        }
    }

}
