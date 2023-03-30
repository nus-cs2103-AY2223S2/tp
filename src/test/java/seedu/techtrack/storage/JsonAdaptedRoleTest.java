package seedu.techtrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.techtrack.storage.JsonAdaptedRole.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.techtrack.testutil.Assert.assertThrows;
import static seedu.techtrack.testutil.TypicalRoles.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.techtrack.commons.exceptions.IllegalValueException;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Phone;

public class JsonAdaptedRoleTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_JOBDESCRIPTION = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_EXPERIENCE = " ";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_COMPANY = BENSON.getCompany().toString();
    private static final String VALID_JOBDESCRIPTION = BENSON.getCompany().toString();
    private static final String VALID_SALARY = BENSON.getSalary().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final String VALID_WEBSITE = "www.google.com";
    private static final String VALID_EXPERIENCE = "C - 1 Year";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRoleDetails_returnsRole() throws Exception {
        JsonAdaptedRole role = new JsonAdaptedRole(BENSON);
        assertEquals(BENSON, role.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(null, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, null, VALID_EMAIL, VALID_COMPANY, VALID_JOBDESCRIPTION,
                VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, null, VALID_COMPANY, VALID_JOBDESCRIPTION,
                VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedRole role =
                new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_JOBDESCRIPTION,
                        VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_invalidJobDescription_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                INVALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = JobDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_nullJobDescription_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                null, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_invalidExperience_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, INVALID_EXPERIENCE);
        String expectedMessage = Experience.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_nullExperience_throwsIllegalValueException() {
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, VALID_TAGS, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Experience.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, role::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRole role = new JsonAdaptedRole(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COMPANY,
                VALID_JOBDESCRIPTION, invalidTags, VALID_WEBSITE, VALID_SALARY, VALID_DEADLINE, VALID_EXPERIENCE);
        assertThrows(IllegalValueException.class, role::toModelType);
    }

}
