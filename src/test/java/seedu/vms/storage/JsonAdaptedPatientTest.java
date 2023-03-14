package seedu.vms.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DOB = "123-03-23";
    private static final String INVALID_ALLERGY = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_DOB = BENSON.getDob().toString();
    private static final String VALID_BLOODTYPE = BENSON.getBloodType().toString();
    private static final List<JsonAdaptedGroupName> VALID_ALLERGIES = BENSON.getAllergy().stream()
            .map(JsonAdaptedGroupName::fromModelType)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedGroupName> VALID_VACCINES = BENSON.getVaccine().stream()
            .map(JsonAdaptedGroupName::fromModelType)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, patient.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_DOB, VALID_BLOODTYPE,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(null, VALID_PHONE, VALID_DOB, VALID_BLOODTYPE,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_DOB, VALID_BLOODTYPE,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, null, VALID_DOB, VALID_BLOODTYPE,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDob_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_DOB, VALID_BLOODTYPE,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = Dob.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDob_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_BLOODTYPE,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_DOB, INVALID_ADDRESS,
                VALID_ALLERGIES, VALID_VACCINES);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_DOB, null, VALID_ALLERGIES,
                VALID_VACCINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<JsonAdaptedGroupName> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new JsonAdaptedGroupName(INVALID_ALLERGY));
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_DOB, VALID_BLOODTYPE,
                invalidAllergies, VALID_VACCINES);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

}
