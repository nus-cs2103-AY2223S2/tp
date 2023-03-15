package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedUser.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BART;
import static seedu.address.testutil.TypicalUser.LINUS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;

public class JsonAdaptedUserTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM_HANDLE = "rachel";
    private static final Integer INVALID_CONTACT_INDEX = 1;
    private static final String INVALID_GROUP_TAG = "#friend";
    private static final String INVALID_MODULE_TAG = "M11";

    private static final String VALID_NAME = BART.getName().toString();
    private static final String VALID_PHONE = BART.getPhone().toString();
    private static final String VALID_EMAIL = BART.getEmail().toString();
    private static final Integer VALID_CONTACT_INDEX = 0;
    private static final String VALID_ADDRESS = BART.getAddress().toString();
    private static final String VALID_TELEGRAM_HANDLE = BART.getTelegramHandle().toString();
    private static final List<JsonAdaptedGroupTag> VALID_TAGS = BART.getImmutableGroupTags().stream()
            .map(JsonAdaptedGroupTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedModuleTag> VALID_MODULE_TAGS =
            BART.getImmutableModuleTags().stream()
                    .map(JsonAdaptedModuleTag::new)
                    .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedUser user = new JsonAdaptedUser(LINUS);
        assertEquals(LINUS, user.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX,
                        VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX, VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX, VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX, VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX, VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_userContactIndexNull_throwsIllegalValueException() {
        JsonAdaptedPerson user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, null, VALID_TAGS, VALID_MODULE_TAGS);
        assertThrows(IllegalValueException.class, () -> user.toModelType());
    }

    @Test
    public void toModelType_personContactIndexNull_throwsIllegalValueException() {
        JsonAdaptedPerson user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, null, VALID_TAGS, VALID_MODULE_TAGS);
        assertThrows(IllegalValueException.class, () -> user.toModelType());
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX, VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX, VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX,
                VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX,
                        VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_CONTACT_INDEX,
                VALID_TAGS, VALID_MODULE_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedGroupTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedGroupTag(INVALID_GROUP_TAG));
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TELEGRAM_HANDLE, VALID_CONTACT_INDEX,
                        invalidTags, VALID_MODULE_TAGS);
        assertThrows(IllegalValueException.class, user::toModelType);
    }

}
