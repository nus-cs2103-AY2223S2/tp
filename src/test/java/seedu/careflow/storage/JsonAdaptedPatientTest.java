package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.careflow.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.model.patient.Address;
import seedu.careflow.model.patient.DateOfBirth;
import seedu.careflow.model.patient.DrugAllergy;
import seedu.careflow.model.patient.Email;
import seedu.careflow.model.patient.Gender;
import seedu.careflow.model.patient.Ic;
import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.Phone;
import seedu.careflow.model.util.SampleDataUtil;
import seedu.careflow.testutil.PatientBuilder;


class JsonAdaptedPatientTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234*";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DOB = "99-01-1999";
    private static final String INVALID_GENDER = "LOL";
    private static final String INVALID_IC = "Taaaaaaaa";
    private static final String INVALID_DRUG_ALLERGY = "()";
    private static final String INVALID_EMERGENCY_CONTACT = "@11";

    private static final Patient SAMPLE_PATIENT = SampleDataUtil.getSamplePatients()[0];
    private static final String VALID_NAME = SAMPLE_PATIENT.getName().fullName;
    private static final String VALID_PHONE = SAMPLE_PATIENT.getPhone().value;
    private static final String VALID_EMAIL = SAMPLE_PATIENT.getEmail().value;
    private static final String VALID_ADDRESS = SAMPLE_PATIENT.getAddress().value;
    private static final String VALID_DOB = SAMPLE_PATIENT.getBirthDate().value;
    private static final String VALID_GENDER = SAMPLE_PATIENT.getGender().value;
    private static final String VALID_IC = SAMPLE_PATIENT.getIc().value;
    private static final String VALID_DRUG_ALLERGY = SAMPLE_PATIENT.getDrugAllergy().toString();
    private static final String VALID_EMERGENCY_CONTACT = SAMPLE_PATIENT.getEmergencyContact().value;


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(SAMPLE_PATIENT);
        assertEquals(SAMPLE_PATIENT, patient.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDob_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDob_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB,
                        INVALID_GENDER, VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, null,
                        VALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidIC_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        INVALID_IC, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullIC_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        null, VALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDrugAllergy_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, INVALID_DRUG_ALLERGY, VALID_EMERGENCY_CONTACT);
        String expectedMessage = DrugAllergy.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDrugAllergy_noException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, null, VALID_EMERGENCY_CONTACT);
        try {
            assertTrue((new PatientBuilder(patient.toModelType()).build())
                    .isSamePatient(new PatientBuilder(SAMPLE_PATIENT).withDrugAllergy(null).build()));
        } catch (IllegalValueException e) {
            fail();

        }
    }

    @Test
    public void toModelType_invalidEmergencyContact_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, INVALID_EMERGENCY_CONTACT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmergencyContact_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DOB, VALID_GENDER,
                        VALID_IC, VALID_DRUG_ALLERGY, null);
        try {
            assertTrue((new PatientBuilder(patient.toModelType()).build())
                    .isSamePatient(new PatientBuilder(SAMPLE_PATIENT).withEmergencyContact(null).build()));
        } catch (IllegalValueException e) {
            fail();
        }
    }
}
