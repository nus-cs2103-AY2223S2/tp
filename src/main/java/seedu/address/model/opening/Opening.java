package seedu.address.model.opening;

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
public class Opening {

    // Identity fields
    private final Position position;
    private final Company company;
    private final Contact contact;

    // Data fields
    private final Status status;
    private final Set<Date> dates = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Opening(Position position, Company company, Contact contact, Status status, Set<Date> dates) {
        requireAllNonNull(position, company, contact, status, dates);
        this.position = position;
        this.company = company;
        this.contact = contact;
        this.status = status;
        this.dates.addAll(dates);
    }

    public Position getPosition() {
        return position;
    }

    public Company getCompany() {
        return company;
    }

    public Contact getContact() {
        return contact;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable date set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Date> getDates() {
        return Collections.unmodifiableSet(dates);
    }

    /**
     * Returns true if both openings have the same position and company.
     * This defines a weaker notion of equality between two openings.
     */
    public boolean isSameOpening(Opening otherOpening) {
        if (otherOpening == this) {
            return true;
        }

        return otherOpening != null
                && otherOpening.getPosition().equals(getPosition())
                && otherOpening.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both openings have the same identity and data fields.
     * This defines a stronger notion of equality between two openings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Opening)) {
            return false;
        }

        Opening otherOpening = (Opening) other;
        return otherOpening.getPosition().equals(getPosition())
                && otherOpening.getCompany().equals(getCompany())
                && otherOpening.getContact().equals(getContact())
                && otherOpening.getStatus().equals(getStatus())
                && otherOpening.getDates().equals(getDates());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(position, company, contact, status, dates);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPosition())
                .append(" Company: ")
                .append(getCompany())
                .append(" Contact: ")
                .append(getContact())
                .append(" Status: ")
                .append(getStatus())
                .append(" Dates: ");


        Set<Date> dates = getDates();
        if (!dates.isEmpty()) {
            builder.append("; Dates: ");
            dates.forEach(builder::append);
        }
        return builder.toString();
    }

}
