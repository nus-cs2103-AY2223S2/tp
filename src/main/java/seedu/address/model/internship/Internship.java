package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Internship in InternBuddy.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    // Identity fields
    private final CompanyName companyName;
    private final Role role;

    // Data fields
    private final Status status;

    private final Date date;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs an Internship instance where every field must be present and not null.
     *
     * @param companyName The name of the company associated with the Internship.
     * @param role The role or job position associated with the Internship.
     * @param status The status of the Internship application.
     * @param tags The set of tags associated with the Internship.
     */
    public Internship(CompanyName companyName, Role role, Status status, Date date, Set<Tag> tags) {
        requireAllNonNull(companyName, role, status, date, tags);
        this.companyName = companyName;
        this.role = role;
        this.status = status;
        this.date = date;
        this.tags.addAll(tags);
    }

    /**
     * Gets the company name for the Internship entry.
     *
     * @return the company name associated with the Internship entry.
     */
    public CompanyName getCompanyName() {
        return companyName;
    }

    /**
     * Gets the role for the Internship entry.
     *
     * @return the role associated with the Internship entry.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the status for the Internship entry.
     *
     * @return the status associated with the Internship entry.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the date for the Internship entry.
     *
     * @return the date associated with the Internship entry.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return the set of tags associated with this Internship.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Internships have the same company name.
     * This defines a weaker notion of equality between two Internships
     *
     * @param otherInternship The Internship to compare with.
     * @return true if both Internships have the same company name.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getCompanyName().equals(getCompanyName());
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two Internships.
     *
     * @param other The other Internship to compare with.
     * @return true if both internships have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherPerson = (Internship) other;
        return otherPerson.getCompanyName().equals(getCompanyName())
                && otherPerson.getRole().equals(getRole())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, role, status, date, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append("; Role: ")
                .append(getRole())
                .append("; Status: ")
                .append(getStatus())
                .append("; Date: ")
                .append(getDate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
