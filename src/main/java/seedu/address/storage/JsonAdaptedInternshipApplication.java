package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.JobTitle;

/**
 * Jackson-friendly version of {@link seedu.address.model.person.InternshipApplication}.
 */
public class JsonAdaptedInternshipApplication {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship application 's %s field is missing!";

    private final String companyName;
    private final String jobTitle;
    /**
     * Constructs a {@code JsonAdaptedInternshipApplication} with the given InternshipApplication details.
     */
    @JsonCreator
    public JsonAdaptedInternshipApplication(@JsonProperty("companyName") String companyName,
                                            @JsonProperty("jobTitle") String jobTitle) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
    }

    /**
     * Converts a given {@code InternshipApplication} into this class for Jackson use.
     */
    public JsonAdaptedInternshipApplication(InternshipApplication source) {
        companyName = source.getCompanyName().fullName;
        jobTitle = source.getJobTitle().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted InternshipApplication object
     * into the model's {@code InternshipApplication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted InternshipApplication.
     */
    public InternshipApplication toModelType() throws IllegalValueException {
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelJobTitle = new JobTitle(jobTitle);
        return new InternshipApplication(modelCompanyName, modelJobTitle);
    }
}
