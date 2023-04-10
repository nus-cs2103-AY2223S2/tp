package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.documents.Documents;

/**
 * Represents an InternshipApplication in the tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipApplication implements Comparable<InternshipApplication> {
    // Identity fields
    private final CompanyName companyName;
    private final JobTitle jobTitle;
    private final Set<Review> reviews = new HashSet<>();
    private final Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();
    private final Set<Qualification> qualifications = new HashSet<>();
    private final Location location;
    private final Salary salary;
    private final Set<Note> notes = new HashSet<>();
    private final Rating rating;
    private final Set<Reflection> reflections = new HashSet<>();

    // Interview fields
    private final InterviewDate interviewDate;
    private final InternshipStatus status;
    private final boolean isArchived;

    // Data fields
    private final Contact contact;
    private final Documents documents;

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job) {

        requireAllNonNull(name, job);
        //Identity field
        this.companyName = name;
        this.jobTitle = job;
        this.location = null;
        this.salary = null;
        this.rating = null;

        // Data field
        this.contact = null;
        this.status = InternshipStatus.PENDING;
        this.isArchived = false;
        this.documents = null;

        //Interview field
        this.interviewDate = null;
    }

    /**
     * Company name and job title field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Set<Review> reviews, InternshipStatus status,
                                 Documents documents) {
        requireAllNonNull(name, job);
        this.companyName = name;
        this.jobTitle = job;
        this.location = null;
        this.salary = null;
        this.rating = null;

        this.reviews.addAll(reviews);
        this.contact = null;
        this.status = status;
        this.isArchived = false;
        this.interviewDate = null;
        this.documents = documents;
    }

    /**
     * Company name and job title field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Set<Review> reviews,
        Set<ProgrammingLanguage> programmingLanguages, Set<Qualification> qualifications, Location location,
        Salary salary, Set<Note> notes, Rating rating, Set<Reflection> reflections) {

        requireAllNonNull(name, job);
        //Identity field
        this.companyName = name;
        this.jobTitle = job;
        this.reviews.addAll(reviews);
        this.programmingLanguages.addAll(programmingLanguages);
        this.qualifications.addAll(qualifications);
        this.location = location;
        this.salary = salary;
        this.notes.addAll(notes);
        this.rating = rating;
        this.reflections.addAll(reflections);

        // Data field
        this.contact = null;
        this.status = InternshipStatus.PENDING;
        this.isArchived = false;
        this.documents = null;

        //Interview field
        this.interviewDate = null;
    }

    /**
     * Every field must be present and not null.
     */
    public InternshipApplication(CompanyName name, JobTitle job, Set<Review> reviews, Contact contact,
                                 InternshipStatus status, boolean isArchived, Documents documents) {
        requireAllNonNull(name, job);
        this.companyName = name;
        this.jobTitle = job;
        this.reviews.addAll(reviews);
        this.contact = contact;
        this.status = status;
        this.interviewDate = null;
        this.location = null;
        this.salary = null;
        this.rating = null;
        this.documents = documents;
        this.isArchived = isArchived;
    }

    /**
     * The company name and job title field must be present and not null.
     */
    public InternshipApplication(CompanyName companyName, JobTitle job, Set<Review> reviews,
        Set<ProgrammingLanguage> programmingLanguages, Set<Qualification> qualifications, Location location,
        Salary salary, Set<Note> notes, Rating rating, Set<Reflection> reflections, Contact contact,
        InternshipStatus status, boolean isArchived, InterviewDate interviewDate, Documents documents) {
        requireAllNonNull(companyName, job);
        //Identity field
        this.companyName = companyName;
        this.jobTitle = job;
        this.reviews.addAll(reviews);
        this.programmingLanguages.addAll(programmingLanguages);
        this.qualifications.addAll(qualifications);
        this.location = location;
        this.salary = salary;
        this.notes.addAll(notes);
        this.rating = rating;
        this.reflections.addAll(reflections);

        // Data field
        this.contact = contact;
        this.status = status;
        this.isArchived = isArchived;
        this.documents = documents;

        //Interview field
        this.interviewDate = interviewDate;
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

    public Set<ProgrammingLanguage> getProgrammingLanguages() {
        return Collections.unmodifiableSet(programmingLanguages);
    }

    public Set<Qualification> getQualifications() {
        return Collections.unmodifiableSet(qualifications);
    }

    public Location getLocation() {
        return location;
    }

    public Salary getSalary() {
        return salary;
    }

    public Set<Note> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    public Rating getRating() {
        return rating;
    }

    public Set<Reflection> getReflections() {
        return Collections.unmodifiableSet(reflections);
    }

    public InternshipStatus getStatus() {
        return status;
    }

    public Contact getContact() {
        return contact;
    }

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    public boolean isArchived() {
        return isArchived;
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
    public int compareTo(InternshipApplication internshipApplication) {
        return this.getCompanyName().compareTo(internshipApplication.getCompanyName());
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
                .append(getStatus())
                .append("; Archived: ")
                .append(isArchived());

        if (!reviews.isEmpty()) {
            builder.append("; Review: ");
            reviews.forEach(builder::append);
        }

        if (!programmingLanguages.isEmpty()) {
            builder.append("; Programming Language: ");
            programmingLanguages.forEach(builder::append);
        }

        if (!qualifications.isEmpty()) {
            builder.append("; Qualification: ");
            qualifications.forEach(builder::append);
        }

        if (location != null && location.value != null && !location.value.isBlank()) {
            builder.append("; Location: ")
                    .append(getLocation());
        }

        if (salary != null && salary.value != null && !salary.value.isBlank()) {
            builder.append("; Salary: ")
                    .append(getSalary());
        }

        if (!notes.isEmpty()) {
            builder.append("; Note: ");
            notes.forEach(builder::append);
        }

        if (rating != null && rating.value != null && !rating.value.isBlank()) {
            builder.append("; Rating: ")
                    .append(getRating());
        }

        if (!reflections.isEmpty()) {
            builder.append("; Reflection: ");
            reflections.forEach(builder::append);
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
