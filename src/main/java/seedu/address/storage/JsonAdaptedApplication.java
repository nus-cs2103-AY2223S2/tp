package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Application;
import seedu.address.model.application.CompanyEmail;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.Role;
import seedu.address.model.application.Status;


/**
 * Jackson-friendly version of {@link Application}.
 */
class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    private final String companyName;
    private final String companyEmail;
    private final String status;
    private final String role;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedApplication(@JsonProperty("companyName") String companyName,
                                  @JsonProperty("companyEmail") String companyEmail,
                                  @JsonProperty("status") String status,
                                  @JsonProperty("role") String role) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.status = status;
        this.role = role;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedApplication(Application source) {
        companyName = source.getCompanyName().name;
        status = source.getStatus().value.toString();
        companyEmail = source.getCompanyEmail().value;
        role = source.getRole().roleApplied;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Application toModelType() throws IllegalValueException {

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (companyEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyEmail.class.getSimpleName()));
        }
        if (!CompanyEmail.isValidEmail(companyEmail)) {
            throw new IllegalValueException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        final CompanyEmail modelCompanyEmail = new CompanyEmail(companyEmail);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);
        return new Application(modelRole, modelCompanyName, modelCompanyEmail, modelStatus);
    }

}
