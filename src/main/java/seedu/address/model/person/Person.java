package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Note;

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
    private final Set<Note> notes = new HashSet<>();
    private Optional<InterviewDateTime> interviewDateTime = Optional.empty();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Status status, Set<Note> notes) {
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
    public Set<Note> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    /**
     * Returns the {@code Optional<InterviewDateTime>} for the applicant.
     * @return Interview date of the applicant.
     */
    public Optional<InterviewDateTime> getInterviewDateTime() {
        return interviewDateTime;
    }

    /**
     * Sets Status for applicants.
     * @param status new Status for the applicant.
     */
    private void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Advances status of applicants, according to application cycle
     */
    public void advancePerson() {
        switch (this.status) {
        case APPLIED:
            setStatus(Status.SHORTLISTED);
            break;
        case SHORTLISTED:
            setStatus(Status.ACCEPTED);
            break;
        default:
            throw new AssertionError("This person's application status cannot be advanced!");
        }
    }

    /**
     * Changes the status of the applicant to Rejected
     */
    public void rejectPerson() {
        this.status = Status.REJECTED;
    }

    /**
     * Sets interview dateTime for shortlisted applicants.
     * @param interviewDateTime Interview dateTime for the applicant.
     */
    public void setInterviewDateTime(Optional<InterviewDateTime> interviewDateTime) {
        this.interviewDateTime = interviewDateTime;
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
    public boolean canAdvance() {
        return this.status != Status.REJECTED && this.status != Status.ACCEPTED;
    }

    /**
     * Returns true if the person status can be rejected otherwise false.
     */
    public boolean canReject() {
        return this.status != Status.REJECTED;
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
                && otherPerson.getNotes().equals(getNotes())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getInterviewDateTime().equals(getInterviewDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, notes, status, interviewDateTime);
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
                .append("; Status: ")
                .append(getStatus())
                .append("; Interview Date: ")
                .append(getInterviewDateTime());

        Set<Note> tags = getNotes();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
