package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.InterviewDate;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Review;
import seedu.address.model.contact.Contact;
import seedu.address.model.documents.Documents;

/**
 * A utility class to help with building InternshipApplication objects.
 */
public class InternshipBuilder {
    public static final String DEFAULT_COMPANY_NAME = "Company A";
    public static final String DEFAULT_JOB_TITLE = "Position A";

    private CompanyName companyName;
    private JobTitle jobTitle;
    private Set<Review> reviews;
    private Documents documents;
    private Contact contact;
    private InternshipStatus status;
    private InterviewDate interviewDate;
    private boolean isArchived;

    /**
     * Creates an {@code InternshipApplicationBuilder} with the default details.
     */
    public InternshipBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
        reviews = new HashSet<>();
        status = InternshipStatus.PENDING;
        isArchived = false;
        interviewDate = null;
    }

    /**
     * Initializes the InternshipApplicationBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(InternshipApplication internshipToCopy) {
        companyName = internshipToCopy.getCompanyName();
        jobTitle = internshipToCopy.getJobTitle();
        reviews = new HashSet<>(internshipToCopy.getReviews());
        documents = internshipToCopy.getDocuments();
        contact = internshipToCopy.getContact();
        status = internshipToCopy.getStatus();
        isArchived = internshipToCopy.isArchived();
        interviewDate = internshipToCopy.getInterviewDate();
    }

    /**
     * Sets the {@code CompanyName} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withCompanyName(String companyName) {
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    /**
     * Sets the {@code Documents} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withDocuments(Documents documents) {
        this.documents = documents;
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withStatus(InternshipStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the boolean {@code isArchived} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
        return this;
    }

    /**
     * Sets the {@code InterviewDate} of the {@code InternshipApplication} that we are building.
     */
    public InternshipBuilder withInterviewDate(InterviewDate interviewDate) {
        this.interviewDate = interviewDate;
        return this;
    }

    public InternshipApplication build() {
        return new InternshipApplication(companyName, jobTitle, reviews, contact, status, isArchived, documents);
    }
}
