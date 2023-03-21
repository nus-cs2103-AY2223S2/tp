package seedu.internship.model.internship;

import static seedu.internship.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.internship.model.tag.Tag;

/**
 * Represents an Internship in the internship catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    private final Position position;
    private final Company company;
    private final Status status;
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Internship(Position position, Company company, Status status, Description description, Set<Tag> tags) {
        requireAllNonNull(position, company, status, description, tags);
        this.position = position;
        this.company = company;
        this.status = status;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Position getPosition() {
        return position;
    }

    public Company getCompany() {
        return company;
    }

    public Status getStatus() {
        return status;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both internships have the same position and company name.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getPosition().equals(getPosition())
                && otherInternship.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two internships.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return otherInternship.getPosition().equals(getPosition())
                && otherInternship.getCompany().equals(getCompany())
                && otherInternship.getStatus().equals(getStatus())
                && otherInternship.getDescription().equals(getDescription())
                && otherInternship.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(position, company, status, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPosition())
                .append("; Company: ")
                .append(getCompany())
                .append("; Status: ")
                .append(getStatus())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
