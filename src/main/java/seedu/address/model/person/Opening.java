package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Deadline;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Opening {

    // Identity fields
    private final Company company;
    private final Position position;
    private final Status status;
    private final Set<Deadline> deadlines = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Opening(Company company, Position position, Status status, HashSet<Deadline> deadlines) {
        requireAllNonNull(company, position, status);
        this.company = company;
        this.position = position;
        this.status = status;
        this.deadlines.addAll(deadlines);
    }

    public Company getCompany() {
        return company;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Deadline> getDeadlines() {
        return Collections.unmodifiableSet(deadlines);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameOpening(Opening otherOpening) {
        if (otherOpening == this) {
            return true;
        }

        return otherOpening != null
                && otherOpening.getCompany().equals(getCompany())
                && otherOpening.getPosition().equals(getPosition());
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

        if (!(other instanceof Opening)) {
            return false;
        }

        Opening otherPerson = (Opening) other;
        return otherPerson.getCompany().equals(getCompany())
                && otherPerson.getPosition().equals(getPosition())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getDeadlines().equals(getDeadlines());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, position, status, deadlines);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompany())
                .append("; Position: ")
                .append(getPosition())
                .append("; Status: ")
                .append(getStatus());

        Set<Deadline> deadlines = getDeadlines();
        if (!deadlines.isEmpty()) {
            builder.append("; Deadlines: ");
            deadlines.forEach(builder::append);
        }
        return builder.toString();
    }

}
