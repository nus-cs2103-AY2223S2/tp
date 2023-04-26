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
import seedu.address.model.person.patient.Remark;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;

public class JsonAdaptedPatientTest {
    private static final String INVALID_HEIGHT_STRING = "five";
    private static final String INVALID_HEIGHT_NEGATIVE = "-3";
    private static final String INVALID_HEIGHT_TOO_LITTLE_DECIMAL_POINT = "2.1";
    private static final String INVALID_HEIGHT_TOO_MANY_DECIMAL_POINT = "2.144";
    private static final String INVALID_WEIGHT_STRING = "thirty";
    private static final String INVALID_WEIGHT_NEGATIVE = "-30";
    private static final String INVALID_WEIGHT_TOO_MANY_DECIMAL_POINT = "30.000";
    private static final String INVALID_DIAGNOSIS = "#$%thyroid";
    private static final String INVALID_STATUS = "Complaining";
    private static final String INVALID_REMARK = "";

    private static final String VALID_NAME = ZAYDEN.getName().getValue();
    private static final String VALID_PHONE = ZAYDEN.getPhone().getValue();
    private static final String VALID_EMAIL = ZAYDEN.getEmail().getValue();
    private static final List<JsonAdaptedTag> VALID_TAGS = ZAYDEN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_HEIGHT = ZAYDEN.getHeight().getValue();
    private static final String VALID_WEIGHT = ZAYDEN.getWeight().getValue();
    private static final String VALID_DIAGNOSIS = ZAYDEN.getDiagnosis().getValue();
    private static final String VALID_STATUS = ZAYDEN.getStatus().getValue();
    private static final String VALID_REMARK = ZAYDEN.getRemark().getValue();

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(ZAYDEN);
        assertEquals(ZAYDEN, patient.toModelType());
    }

    @Test
    public void toModelType_invalidHeightString_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HEIGHT_STRING, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidHeightNegative_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HEIGHT_NEGATIVE, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidHeightTooManyDecimalPoint_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HEIGHT_TOO_MANY_DECIMAL_POINT,
                VALID_WEIGHT, VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidHeightTooLittleDecimalPoint_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HEIGHT_TOO_LITTLE_DECIMAL_POINT,
                VALID_WEIGHT, VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidWeightString_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, INVALID_WEIGHT_STRING,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidWeightNegative_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, INVALID_WEIGHT_NEGATIVE,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidWeightTooManyDecimalPoint_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT,
                INVALID_WEIGHT_TOO_MANY_DECIMAL_POINT, VALID_DIAGNOSIS, VALID_STATUS,
                VALID_REMARK, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, null,
                VALID_DIAGNOSIS, VALID_STATUS, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Weight.class.getSimpleName());
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
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Diagnosis.class.getSimpleName());
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
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, INVALID_REMARK, VALID_TAGS);
        String expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HEIGHT, VALID_WEIGHT,
                VALID_DIAGNOSIS, VALID_STATUS, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }


}
