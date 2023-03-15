package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.ML1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Description;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_POSITION = "Engineer&";
    private static final String INVALID_COMPANY = "";
    private static final String INVALID_STATUS = "5";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_TAG = "imp and fun";

    private static final String VALID_POSITION = ML1.getPosition().toString();
    private static final String VALID_COMPANY = ML1.getCompany().toString();
    private static final String VALID_STATUS = ML1.getStatus().toString();
    private static final String VALID_DESCRIPTION = ML1.getDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ML1.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(ML1);
        assertEquals(ML1, internship.toModelType());
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_POSITION, VALID_COMPANY, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(null, VALID_COMPANY, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_POSITION, INVALID_COMPANY, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_POSITION, null, VALID_STATUS, VALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_POSITION, VALID_COMPANY, INVALID_STATUS, VALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_POSITION, VALID_COMPANY, null, VALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_POSITION, VALID_COMPANY, VALID_STATUS, INVALID_DESCRIPTION, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

//    @Test
//    public void toModelType_nullDescription_throwsIllegalValueException() {
//        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_POSITION, VALID_COMPANY, VALID_STATUS, null, VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
//    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_POSITION, VALID_COMPANY, VALID_STATUS, VALID_DESCRIPTION, invalidTags);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

}

