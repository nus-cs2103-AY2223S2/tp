package seedu.patientist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.patientist.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalPatients.AMY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PID = "213545X";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = AMY.getName().toString();
    private static final String VALID_ID = AMY.getIdNumber().toString();
    private static final String VALID_PHONE = AMY.getPhone().toString();
    private static final String VALID_EMAIL = AMY.getEmail().toString();
    private static final String VALID_ADDRESS = AMY.getAddress().toString();
    private static final String VALID_PRIORITY = "LOW";
    private static final List<JsonAdaptedStatus> VALID_STATUS = AMY.getPatientStatusDetails().stream()
            .map(JsonAdaptedStatus::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedToDo> VALID_TODO = List.of(new JsonAdaptedToDo("Take meds"));
    private static final List<JsonAdaptedTag> VALID_TAGS = AMY.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient person = new JsonAdaptedPatient(AMY);
        assertEquals(AMY, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(INVALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_PRIORITY, VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(null, VALID_ID, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_PRIORITY,
                        VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_PRIORITY, VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, null, VALID_EMAIL,
                        VALID_ADDRESS, VALID_PRIORITY, VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_PRIORITY, VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, VALID_PHONE, null,
                        VALID_ADDRESS, VALID_PRIORITY, VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_PRIORITY, VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL, null, VALID_PRIORITY,
                        VALID_STATUS, VALID_TODO, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));

        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_PRIORITY,
                        VALID_STATUS, VALID_TODO, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
