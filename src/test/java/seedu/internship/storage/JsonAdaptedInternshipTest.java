package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.GOOGLE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_COMPANY_NAME = "This company name has more than fifty characters hmm";
    private static final String INVALID_ROLE = "This role has more than fifty characters so it is wrong";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_DATE = "1st March 2023";
    private static final String INVALID_COMMENT = "";
    private static final String INVALID_TAG = "";

    private static final String VALID_COMPANY_NAME = GOOGLE.getCompanyName().toString();
    private static final String VALID_ROLE = GOOGLE.getRole().toString();
    private static final String VALID_STATUS = GOOGLE.getStatus().toString();
    private static final String VALID_DATE = GOOGLE.getDate().toString();
    private static final String VALID_COMMENT = GOOGLE.getComment().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = GOOGLE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(GOOGLE);
        assertEquals(GOOGLE, internship.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_COMPANY_NAME, VALID_ROLE, VALID_STATUS, VALID_DATE, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new
                JsonAdaptedInternship(null, VALID_ROLE, VALID_STATUS, VALID_DATE, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, INVALID_ROLE, VALID_STATUS, VALID_DATE, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, null, VALID_STATUS, VALID_DATE, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE, INVALID_STATUS, VALID_DATE, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE, null, VALID_DATE, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE, VALID_STATUS, INVALID_DATE, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE, VALID_STATUS, null, VALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidComment_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE, VALID_STATUS, VALID_DATE, INVALID_COMMENT,
                        VALID_TAGS);
        String expectedMessage = Comment.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE, VALID_STATUS, VALID_DATE, VALID_COMMENT,
                        invalidTags);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }
}
