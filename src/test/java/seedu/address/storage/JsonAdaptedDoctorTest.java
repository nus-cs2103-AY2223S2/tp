package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDoctor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctors.ALICE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.doctor.Specialty;
import seedu.address.model.person.doctor.Yoe;

public class JsonAdaptedDoctorTest {
    private static final String INVALID_SPECIALTY = "#lung-specialist";
    private static final String EMPTY_SPECIALTY = "";
    private static final String INVALID_YOE_STRING = "test";
    private static final String INVALID_YOE_NEGATIVE = "-1";
    private static final String INVALID_YOE_DECIMAL = "2.5";

    private static final String VALID_NAME = ALICE.getName().toString();
    private static final String VALID_PHONE = ALICE.getPhone().toString();
    private static final String VALID_EMAIL = ALICE.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ALICE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_SPECIALTY = ALICE.getSpecialty().specialty;
    private static final String VALID_YOE = ALICE.getYoe().value;
    private static final List<JsonAdaptedPatient> VALID_PATIENTS = ALICE.getPatients().stream()
            .map(JsonAdaptedPatient::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validDoctorDetails_returnsDoctor() throws Exception {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(ALICE);
        assertEquals(ALICE, doctor.toModelType());
    }

    @Test
    public void toModelType_invalidSpecialty_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_SPECIALTY, VALID_YOE, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = Specialty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_emptySpecialty_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, EMPTY_SPECIALTY, VALID_YOE, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = Specialty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullSpecialty_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_YOE, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialty.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidYoeAsString_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_SPECIALTY,
                INVALID_YOE_STRING, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = Yoe.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidYoeAsNegative_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_SPECIALTY,
                INVALID_YOE_NEGATIVE, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = Yoe.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidYoeAsDecimal_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_SPECIALTY,
                INVALID_YOE_DECIMAL, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = Yoe.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullYoe_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_SPECIALTY,
                null, VALID_TAGS, VALID_PATIENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Yoe.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

}
