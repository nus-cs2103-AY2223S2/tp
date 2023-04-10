package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.storage.elderly.JsonAdaptedElderly.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.storage.elderly.JsonAdaptedElderly;


public class JsonAdaptedElderlyTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NRIC = "AAAAA";
    private static final String INVALID_BIRTH_DATE = "random";
    private static final String INVALID_REGION = "south";
    private static final String INVALID_RISK = "hello";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATE = "#friend to dwadawd";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_BIRTH_DATE = BENSON.getBirthDate().toString();
    private static final String VALID_REGION = BENSON.getRegion().toString();
    private static final String VALID_RISK = BENSON.getRiskLevel().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAvailableDate> VALID_DATES = BENSON.getAvailableDates()
            .stream().map(JsonAdaptedAvailableDate::new).collect(Collectors.toList());

    private static final FriendlyLink appTestCache = new FriendlyLink();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(BENSON);
        assertTrue(BENSON.isSamePerson(elderly.toModelType(appTestCache)));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_NRIC, VALID_RISK, VALID_BIRTH_DATE, VALID_REGION, VALID_TAGS, VALID_DATES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, INVALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = BirthDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, null, VALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BirthDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidRegion_throwsIllegalValueException() {
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, INVALID_REGION, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = Region.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_nullRegion_throwsIllegalValueException() {
        JsonAdaptedElderly elderly = new JsonAdaptedElderly(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NRIC, VALID_BIRTH_DATE, null, VALID_RISK, VALID_TAGS, VALID_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Region.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> elderly.toModelType(appTestCache));
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, invalidTags, VALID_DATES);
        assertThrows(IllegalValueException.class, () -> elderly.toModelType(appTestCache));
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        List<JsonAdaptedAvailableDate> invalidDates = new ArrayList<>(VALID_DATES);
        try {
            invalidDates.add(new JsonAdaptedAvailableDate(INVALID_DATE));
        } catch (IllegalValueException e) {
            fail();
        }
        JsonAdaptedElderly elderly =
                new JsonAdaptedElderly(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NRIC, VALID_BIRTH_DATE, VALID_REGION, VALID_RISK, VALID_TAGS, invalidDates);
        assertThrows(IllegalValueException.class, () -> elderly.toModelType(appTestCache));
    }

}
