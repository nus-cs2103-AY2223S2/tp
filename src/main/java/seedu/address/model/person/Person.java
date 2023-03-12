package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
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
    private Status status;
    private final Set<Tag> notes = new HashSet<>();
    private Optional<InterviewDateTime> interviewDateTime = Optional.empty();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Status status, Set<Tag> notes) {
        requireAllNonNull(name, phone, email, address, notes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
        this.notes.addAll(notes);
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

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    /**
     * Returns the interview dateTime for the applicant if present, else throws {@code NoSuchElementException}
     * @return Interview date of the applicant.
     * @throws NoSuchElementException Thrown when no interview date is present.
     */
    public InterviewDateTime getInterviewDateTime() throws NoSuchElementException {
        return interviewDateTime.orElseThrow();
    }

    /**
     * Sets interview dateTime for shortlisted applicants.
     * @param interviewDateTime Interview dateTime for the applicant.
     */
    public void setInterviewDateTime(InterviewDateTime interviewDateTime) {
        this.interviewDateTime = Optional.of(interviewDateTime);
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
     * Returns true if the person status can be advanced otherwise false.
     */
    public boolean advanceStatus() {
        switch (this.status) {
        case APPLIED:
            this.status = Status.SHORTLISTED;
            return true;
        case SHORTLISTED:
            this.status = Status.ACCEPTED;
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns true if the person status can be rejected otherwise false.
     */
    public boolean rejectStatus() {
        if (this.status == status.REJECTED) {
            return false;
        } else {
            return true;
        }
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
                && otherPerson.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, notes);
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

        Set<Tag> tags = getNotes();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
