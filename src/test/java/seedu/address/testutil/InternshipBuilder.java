package seedu.address.testutil;

import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;
/**
 * A utility class to help with building InternshipApplication objects.
 */
public class InternshipBuilder {
    public static final String DEFAULT_COMPANY_NAME = "Company A";
    public static final String DEFAULT_JOB_TITLE = "Position A";

    private CompanyName companyName;
    private JobTitle jobTitle;
    /**
     * Creates an {@code InternshipApplicationBuilder} with the default details.
     */
    public InternshipBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
    }
    /**
     * Initializes the InternshipApplicationBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(InternshipApplication internshipToCopy) {
        companyName = internshipToCopy.getCompanyName();
        jobTitle = internshipToCopy.getJobTitle();
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

    public InternshipApplication build() {
        return new InternshipApplication(companyName, jobTitle);
    }
}
