package seedu.address.model.applicant;

import java.util.Objects;

/**
 * Represents an Applicant in a Listing.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant {
    public static final String MESSAGE_CONSTRAINTS = "Applicants names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final Name name;

    /**
     * Creates an Applicant with a name
     * @param name name of the Applicant
     */
    public Applicant(Name name) {
        this.name = name;
    }

    /**
     * Get the name of the applicant
     * @return The name of the applicant
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between the two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(this.getName());
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        return builder.toString();
    }
}
