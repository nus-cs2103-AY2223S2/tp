package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * Represents an InternshipApplication in the tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipApplication {
    // Identity fields
    private final CompanyName companyName;
    private final JobTitle jobTitle;
    private final InternshipStatus status;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Contact contact;

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job) {
        requireAllNonNull(name, job);
        this.companyName = name;
        this.jobTitle = job;
        this.contact = null;
        this.status = InternshipStatus.NA;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Contact contact) {
        requireAllNonNull(name, job);
        this.companyName = name;
        this.jobTitle = job;
        this.contact = contact;
        this.status = InternshipStatus.NA;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, InternshipStatus status) {
        requireAllNonNull(name, job, status);
        this.companyName = name;
        this.jobTitle = job;
        this.contact = null;
        this.status = status;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Contact contact, InternshipStatus status) {
        requireAllNonNull(name, job, contact, status);
        this.companyName = name;
        this.jobTitle = job;
        this.contact = contact;
        this.status = status;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }
    public JobTitle getJobTitle() {
        return jobTitle;
    }
    public InternshipStatus getStatus() {
        return status;
    }

    public InternshipStatus getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Contact getContact() {
        return contact;
    }

    /**
     * Returns true if both internship applications have the same company name and job title.
     * This defines a weaker notion of equality between two internship applications.
     */
    public boolean isSameApplication(InternshipApplication otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && otherApplication.getCompanyName().equals(getCompanyName())
                && otherApplication.getJobTitle().equals(getJobTitle());
    }

    /**
     * Returns true if both internship applications have the same identity and data fields.
     * This defines a stronger notion of equality between two internship applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipApplication)) {
            return false;
        }

        InternshipApplication otherApplication = (InternshipApplication) other;
        return otherApplication.getCompanyName().equals(getCompanyName())
                && otherApplication.getJobTitle().equals(getJobTitle())
                && otherApplication.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, jobTitle);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append("; Job Title: ")
                .append(getJobTitle())
                .append("; Status: ")
                .append(getStatus());;

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (contact != null) {
            builder.append("; Company Phone: ")
                    .append(contact.getPhone())
                    .append("; Company Email: ")
                    .append(contact.getEmail());
        }
        return builder.toString();
    }

}
