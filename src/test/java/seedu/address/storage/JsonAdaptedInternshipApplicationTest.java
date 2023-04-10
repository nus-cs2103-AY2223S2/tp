package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternshipApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.META;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.CompanyName;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;

public class JsonAdaptedInternshipApplicationTest {

    private static final String INVALID_COMPANY_NAME = "R@chel";
    private static final String INVALID_JOB_TITLE = "soft^^engineer";
    private static final String INVALID_PHONE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final List<String> INVALID_CONTACT = Arrays.asList(INVALID_PHONE);

    private static final String VALID_COMPANY_NAME = META.getCompanyName().toString();
    private static final String VALID_JOB_TITLE = META.getJobTitle().toString();
    private static final String VALID_PHONE = META.getContact().getPhone().toString();
    private static final String VALID_EMAIL = META.getContact().getEmail().toString();
    private static final List<String> VALID_CONTACT = Arrays.asList(VALID_PHONE, VALID_EMAIL);

    private static final List<String> INVALID_CONTACT_PHONE = Arrays.asList(INVALID_PHONE, VALID_EMAIL);
    private static final List<String> INVALID_CONTACT_EMAIL = Arrays.asList(VALID_PHONE, INVALID_EMAIL);

    private static final List<String> PLACEHOLDER_EMPTY_LIST = Collections.emptyList();
    private static final String PLACEHOLDER_EMPTY_STRING = "";

    @Test
    public void toModelType_validInternshipApplicationDetails_returnsInternshipApplication() throws Exception {
        JsonAdaptedInternshipApplication application = new JsonAdaptedInternshipApplication(META);
        assertEquals(META, application.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication internshipApplication =
                new JsonAdaptedInternshipApplication(INVALID_COMPANY_NAME,
                        VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, "PENDING", false, null,
                        PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internshipApplication::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication application = new JsonAdaptedInternshipApplication(null,
                VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, "PENDING", false, null,
                PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication application =
                new JsonAdaptedInternshipApplication(VALID_COMPANY_NAME,
                        VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, "INVALIDStatus", false, null,
                        VALID_CONTACT, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = InternshipStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication application =
                new JsonAdaptedInternshipApplication(VALID_COMPANY_NAME,
                        VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, null, false, null,
                        VALID_CONTACT, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidContact_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication application =
                new JsonAdaptedInternshipApplication(VALID_COMPANY_NAME,
                        VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, "PENDING", false, null,
                        INVALID_CONTACT, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = Contact.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidContactPhone_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication application =
                new JsonAdaptedInternshipApplication(VALID_COMPANY_NAME,
                        VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, "PENDING", false, null,
                        INVALID_CONTACT_PHONE, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidContactEmail_throwsIllegalValueException() {
        JsonAdaptedInternshipApplication application =
                new JsonAdaptedInternshipApplication(VALID_COMPANY_NAME,
                        VALID_JOB_TITLE, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST,
                        PLACEHOLDER_EMPTY_STRING, PLACEHOLDER_EMPTY_LIST, "PENDING", false, null,
                        INVALID_CONTACT_EMAIL, PLACEHOLDER_EMPTY_LIST);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }
}
