package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;


public class JsonAdaptedPersonTest {
    private static final String INVALID_NRIC = "V1234567@";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DATE = "341/01/2000";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DRUG_ALLERGY = " ";
    private static final String INVALID_GENDER = " ";
    private static final String INVALID_DOCTOR = "Ben&";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MEDICINE = "$$";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_DRUG_ALLERGY = BENSON.getDrugAllergy().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_DOCTOR = BENSON.getDoctor().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMedicine> VALID_MEDICINES = BENSON.getMedicines().stream()
            .map(JsonAdaptedMedicine::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NRIC, VALID_NAME, VALID_DATE,
                        VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DRUG_ALLERGY,
                        VALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, INVALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, null, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, INVALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE,
                        INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, null, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER,
                        VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, null,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        null, VALID_DRUG_ALLERGY, VALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, INVALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDoctor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER, INVALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = Doctor.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDoctor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER, null, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDrugAllergy_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, INVALID_DRUG_ALLERGY, VALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = DrugAllergy.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDrugAllergy_throwsIllegalValueException() {
        System.out.println(VALID_DATE);
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, null, VALID_GENDER, VALID_DOCTOR, VALID_TAGS, VALID_MEDICINES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DrugAllergy.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER, VALID_DOCTOR, invalidTags, VALID_MEDICINES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidMedicines_throwsIllegalValueException() {
        List<JsonAdaptedMedicine> invalidMedicines = new ArrayList<>(VALID_MEDICINES);
        invalidMedicines.add(new JsonAdaptedMedicine(INVALID_MEDICINE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_DATE, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DRUG_ALLERGY, VALID_GENDER, VALID_DOCTOR, VALID_TAGS, invalidMedicines);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
