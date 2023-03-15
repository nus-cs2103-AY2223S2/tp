package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
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
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private LocalDateTime time = null;

    private MedicalCondition medicalCondition;
    private Age age;
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, LocalDateTime time) {
        requireAllNonNull(name, phone, email, address, tags, time);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.time = time;
        this.medicalCondition = new MedicalCondition("");
    }

    /**
     * Every field must be present and not null, medical condition will be created without any tag
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  LocalDateTime time, MedicalCondition medicalCondition) {
        requireAllNonNull(name, phone, email, address, tags, time);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.time = time;
        this.medicalCondition = medicalCondition;
    }

    /**
     * Alternative constructor for person with scheduled time.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = new Age("");
        this.tags.addAll(tags);
        this.medicalCondition = new MedicalCondition("");
    }

    /**
     * Every filed must be present and not null
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags,
                  MedicalCondition medicalCondition) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
    }

    /**
     * Every filed must be present and not null
     */
    public Person(Name name, Phone phone, Email email, Address address, Age age, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.tags.addAll(tags);
        this.medicalCondition = new MedicalCondition("");
    }

    /**
     * Every filed must be present and not null
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  MedicalCondition medicalCondition) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = new Age("");
        this.tags.addAll(tags);
        this.medicalCondition = medicalCondition;
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


    public LocalDateTime getTime() {
        return time;
    }

    /**
     * check if the person has scheduled time.
     * @return true if the person has time.
     */
    public boolean hasTime() {
        return this.time != null;
    }

    public Age getAge() {
        return age;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        Set<Tag> allTags = new HashSet<>(tags);
        return Collections.unmodifiableSet(allTags);
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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
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
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
