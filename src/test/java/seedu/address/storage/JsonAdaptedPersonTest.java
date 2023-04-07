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
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Telegram;
import seedu.address.testutil.PersonBuilder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = "   ";
    private static final String INVALID_EDUCATION = "#Year 1";
    private static final String INVALID_MODULE = "#CS2030S";
    private static final String INVALID_TELEGRAM = "@____";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getOptionalPhone().map(Phone::toString).orElse("");
    private static final String VALID_EMAIL = BENSON.getOptionalEmail().map(Email::toString).orElse("");
    private static final String VALID_ADDRESS = BENSON.getOptionalAddress().map(Address::toString).orElse("");
    private static final String VALID_EDUCATION = BENSON.getOptionalEducation().map(Education::toString).orElse("");
    private static final String VALID_REMARK = BENSON.getOptionalRemark().map(Remark::toString).orElse("");
    private static final List<JsonAdaptedModule> VALID_MODULES = BENSON.getModules().stream()
            .map(JsonAdaptedModule::new)
            .collect(Collectors.toList());
    private static final String VALID_TELEGRAM = BENSON.getOptionalTelegram().map(Telegram::toString).orElse("");
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        Person expectedPerson = new PersonBuilder(BENSON).withPhone(null).build();
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        Person expectedPerson = new PersonBuilder(BENSON).withEmail(null).build();
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        Person expectedPerson = new PersonBuilder(BENSON).withAddress(null).build();
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidEducation_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                INVALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = Education.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEducation_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        Person expectedPerson = new PersonBuilder(BENSON).withEducation(null).build();
        assertEquals(expectedPerson, person.toModelType());
    }

    // No test for invalid remark, because remark is never invalid.

    @Test
    public void toModelType_nullRemark_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, null, VALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        Person expectedPerson = new PersonBuilder(BENSON).withRemark(null).build();
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, INVALID_TELEGRAM, VALID_MODULES, VALID_TAGS);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, null, VALID_MODULES, VALID_TAGS);
        Person expectedPerson = new PersonBuilder(BENSON).withTelegram(null).build();
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidModules_throwsIllegalValueException() {
        List<JsonAdaptedModule> invalidModules = new ArrayList<>(VALID_MODULES);
        invalidModules.add(new JsonAdaptedModule(INVALID_MODULE));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, invalidModules, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_EDUCATION, VALID_REMARK, VALID_TELEGRAM, VALID_MODULES, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
