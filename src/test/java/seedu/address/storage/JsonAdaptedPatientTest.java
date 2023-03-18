package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ZAYDEN;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.patient.Diagnosis;
import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;

public class JsonAdaptedPatientTest {
    private static final String INVALID_HEIGHT = "-3";
    private static final String INVALID_WEIGHT = "-30.000";
    private static final String INVALID_DIAGNOSIS = "#$%thyroid";
    private static final String INVALID_STATUS = "Complaining";

    private static final String VALID_NAME = ZAYDEN.getName().toString();
    private static final String VALID_PHONE = ZAYDEN.getPhone().toString();
    private static final String VALID_EMAIL = ZAYDEN.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ZAYDEN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_HEIGHT = ZAYDEN.getHeight().height;
    private static final String VALID_WEIGHT = ZAYDEN.getWeight().weight;
    private static final String VALID_DIAGNOSIS = ZAYDEN.getDiagnosis().diagnosis;
    private static final String VALID_STATUS = ZAYDEN.getStatus().toString();
    private static final String VALID_REMARK = ZAYDEN.getRemark().toString();

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(ZAYDEN);
        assertEquals(ZAYDEN, patient.toModelType());
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HEIGHT, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, INVALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, null,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDiagnosis_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, VALID_WEIGHT,
                INVALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Diagnosis.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDiagnosis_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, VALID_WEIGHT,
                null, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Diagnosis.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, VALID_WEIGHT,
                VALID_DIAGNOSIS, INVALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, VALID_WEIGHT,
                VALID_DIAGNOSIS, null, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }


}
