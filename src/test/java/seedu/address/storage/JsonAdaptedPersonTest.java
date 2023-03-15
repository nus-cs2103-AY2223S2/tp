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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.socialmedia.SocialMedia;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = "   ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MODULE = "CS2!03T";
    private static final String INVALID_BIRTHDAY = "01/01-2000";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final JsonAdaptedPhone VALID_PHONE = new JsonAdaptedPhone(BENSON.getPhone().get());
    private static final JsonAdaptedEmail VALID_EMAIL = new JsonAdaptedEmail(BENSON.getEmail().get());
    private static final JsonAdaptedAddress VALID_ADDRESS = new JsonAdaptedAddress(BENSON.getAddress().get());
    private static final JsonAdaptedBirthday VALID_BIRTHDAY = new JsonAdaptedBirthday(BENSON.getBirthday().get());
    private static final JsonAdaptedSocialMedia VALID_SOCIALMEDIA =
        new JsonAdaptedSocialMedia(BENSON.getSocialMedia().orElse(SocialMedia.create()));
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedModule> VALID_MODULES = BENSON.getModules().stream()
            .map(JsonAdaptedModule::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALMEDIA, VALID_TAGS, VALID_MODULES, VALID_BIRTHDAY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALMEDIA, VALID_TAGS, VALID_MODULES, VALID_BIRTHDAY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPhone invalidPhone = new JsonAdaptedPhone(INVALID_PHONE);
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, invalidPhone, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALMEDIA, VALID_TAGS, VALID_MODULES, VALID_BIRTHDAY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedEmail invalidEmail = new JsonAdaptedEmail(INVALID_EMAIL);
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, invalidEmail, VALID_ADDRESS,
                VALID_SOCIALMEDIA, VALID_TAGS, VALID_MODULES, VALID_BIRTHDAY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedAddress invalidAddress = new JsonAdaptedAddress(INVALID_ADDRESS);
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidAddress,
                VALID_SOCIALMEDIA, VALID_TAGS, VALID_MODULES, VALID_BIRTHDAY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALMEDIA, invalidTags, VALID_MODULES, VALID_BIRTHDAY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidBirthday_throwsIllegalValueException() {
        JsonAdaptedBirthday invalidBirthday = new JsonAdaptedBirthday(INVALID_BIRTHDAY);
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALMEDIA, VALID_TAGS, VALID_MODULES, invalidBirthday);
        assertThrows(IllegalArgumentException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidModules_throwsIllegalValueException() {
        List<JsonAdaptedModule> invalidModules = new ArrayList<>(VALID_MODULES);
        invalidModules.add(new JsonAdaptedModule(INVALID_MODULE));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SOCIALMEDIA, VALID_TAGS, invalidModules, VALID_BIRTHDAY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
