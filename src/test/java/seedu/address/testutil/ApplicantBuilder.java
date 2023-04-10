package seedu.address.testutil;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {
    public static final String DEFAULT_NAME = "Benedict Green";

    private Name name;
    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code ApplicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        this.name = applicantToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new seedu.address.model.applicant.Name(name);
        return this;
    }

    public Applicant build() {
        return new Applicant(name);
    }
}
