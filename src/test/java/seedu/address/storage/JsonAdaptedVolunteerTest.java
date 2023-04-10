package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.storage.volunteer.JsonAdaptedVolunteer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVolunteers.BENSON;
import static seedu.address.testutil.TypicalVolunteers.IDA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.storage.volunteer.JsonAdaptedVolunteer;
import seedu.address.testutil.TypicalElderly;
import seedu.address.testutil.TypicalVolunteers;


public class JsonAdaptedVolunteerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NRIC = "AAAAA";
    private static final String INVALID_BIRTH_DATE = "random";
    private static final String INVALID_REGION = "south";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MEDICAL_TAG = "randomrandom";
    private static final String INVALID_DATE = "0239-12-12 to 0213-231-22";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_BIRTH_DATE = TypicalElderly.BENSON.getBirthDate().toString();
    private static final String VALID_REGION = TypicalVolunteers.BENSON.getRegion().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAvailableDate> VALID_DATES = BENSON.getAvailableDates()
            .stream().map(JsonAdaptedAvailableDate::new).collect(Collectors.toList());
    private static final List<JsonAdaptedMedicalTag> VALID_MEDICAL_TAGS = IDA.getMedicalTags().stream()
            .map(JsonAdaptedMedicalTag::new)
            .collect(Collectors.toList());

    private static final FriendlyLink appTestCache = new FriendlyLink();

    @Test
    public void toModelType_validvolunteerDetails_returnsvolunteer() throws Exception {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(BENSON);
        assertTrue(BENSON.isSamePerson(volunteer.toModelType(appTestCache)));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION,
                        VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION,
                VALID_ADDRESS, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, INVALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = BirthDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, null, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BirthDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidRegion_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, INVALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = Region.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullRegion_throwsIllegalValueException() {
        JsonAdaptedVolunteer volunteer = new JsonAdaptedVolunteer(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, null, VALID_TAGS, VALID_MEDICAL_TAGS, VALID_DATES);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Region.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, invalidTags, VALID_MEDICAL_TAGS, VALID_DATES);
        assertThrows(IllegalValueException.class, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        List<JsonAdaptedAvailableDate> invalidDates = new ArrayList<>(VALID_DATES);
        try {
            invalidDates.add(new JsonAdaptedAvailableDate(INVALID_DATE));
        } catch (IllegalValueException e) {
            fail();
        }
        JsonAdaptedVolunteer volunteer =
                new JsonAdaptedVolunteer(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_MEDICAL_TAGS, invalidDates);

        assertThrows(IllegalValueException.class, () -> volunteer.toModelType(appTestCache));
    }

    @Test
    public void jsonAdaptedVolunteer_constructor() {
        Volunteer target = TypicalVolunteers.CARL;
        JsonAdaptedVolunteer convert = new JsonAdaptedVolunteer(target);
        String output = convert.toString();
    }

}
