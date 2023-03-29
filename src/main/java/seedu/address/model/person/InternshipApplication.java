package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.documents.Documents;
import seedu.address.model.tag.Tag;

/**
 * Represents an InternshipApplication in the tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipApplication {
    // Identity fields
    private final CompanyName companyName;
    private final JobTitle jobTitle;
    private final Set<Review> reviews = new HashSet<>();
    private final InterviewDate interviewDate;
    private final InternshipStatus status;


    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Contact contact;
    private final Documents documents;

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Set<Review> reviews) {
        requireAllNonNull(name, job);
        this.companyName = name;
        this.jobTitle = job;
        this.reviews.addAll(reviews);
        this.contact = null;
        this.status = InternshipStatus.NA;
        this.interviewDate = null;
        this.documents = null;
    }

    /**
     * Company name and job title field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Set<Review> reviews, InternshipStatus status,
                                 Documents documents) {
        requireAllNonNull(name, job);
        this.companyName = name;
        this.jobTitle = job;
        this.reviews.addAll(reviews);
        this.contact = null;
        this.status = status;
        this.interviewDate = null;
        this.documents = documents;
    }

    /**
     * Company name and job title field must be present and not null.
     */
    public InternshipApplication(CompanyName companyName, JobTitle job, Set<Review> reviews,
                            Contact contact, InternshipStatus status, InterviewDate interviewDate,
                                 Documents documents) {
        requireAllNonNull(companyName, job);
        this.companyName = companyName;
        this.jobTitle = job;
        this.reviews.addAll(reviews);
        this.contact = contact;
        this.status = status;
        this.interviewDate = interviewDate;
        this.documents = documents;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }
    public JobTitle getJobTitle() {
        return jobTitle;
    }
    public Set<Review> getReviews() {
        return Collections.unmodifiableSet(reviews);
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

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    public Documents getDocuments() {
        return documents;
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
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!reviews.isEmpty()) {
            builder.append("; Review: ");
            reviews.forEach(builder::append);
        }

        if (contact != null) {
            builder.append("; Company Phone: ")
                    .append(contact.getPhone())
                    .append("; Company Email: ")
                    .append(contact.getEmail());
        }

        if (interviewDate != null) {
            builder.append("; Interview Date: ")
                    .append(getInterviewDate());
        }
        return builder.toString();
    }

}
