package seedu.address.model.documents;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a contact in the internship application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Documents {

    // Identity fields
    private final Resume resume;
    private final CoverLetter coverLetter;

    /**
     * Every field must be present and not null.
     */
    public Documents(Resume resume) {
        requireAllNonNull(resume);
        this.resume = resume;
        this.coverLetter = null;
    }

    /**
     * Every field must be present and not null.
     */
    public Documents(CoverLetter coverLetter) {
        requireAllNonNull(coverLetter);
        this.resume = null;
        this.coverLetter = coverLetter;
    }

    /**
     * Every field must be present and not null.
     */
    public Documents(Resume resume, CoverLetter coverLetter) {
        requireAllNonNull(resume, coverLetter);
        this.resume = resume;
        this.coverLetter = coverLetter;
    }

    public Resume getResume() {
        return resume;
    }

    public CoverLetter getCoverLetter() {
        return coverLetter;
    }

    /**
     * Returns true if both contacts have the same identity.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Documents)) {
            return false;
        }

        Documents otherDocuments = (Documents) other;
        return otherDocuments.getResume().equals(getResume())
                && otherDocuments.getCoverLetter().equals(getCoverLetter());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(resume, coverLetter);
    }

    @Override
    public String toString() {
        String builder = "Resume: "
                + getResume()
                + "; Cover letter: "
                + getCoverLetter();

        return builder;
    }

}
