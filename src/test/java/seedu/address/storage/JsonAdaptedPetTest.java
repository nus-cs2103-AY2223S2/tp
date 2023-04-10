package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPet.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.WOOFERS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Email;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Phone;

public class JsonAdaptedPetTest {
    private static final String INVALID_OWNER_NAME = "R@chel";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_OWNER_NAME = WOOFERS.getOwnerName().toString();
    private static final String VALID_NAME = WOOFERS.getName().toString();
    private static final String VALID_PHONE = WOOFERS.getPhone().toString();
    private static final String VALID_EMAIL = WOOFERS.getEmail().toString();
    private static final String VALID_ADDRESS = WOOFERS.getAddress().toString();
    private static final String VALID_TIMESTAMP = WOOFERS.getTimeStamp().toString();
    private static final String VALID_DEADLINE = WOOFERS.getDeadline().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = WOOFERS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final String VALID_MARK = "Unmarked";
    /*
    @Test
    public void toModelType_validPetDetails_returnsPet() throws Exception {
        JsonAdaptedPet pet = new JsonAdaptedPet(WOOFERS);
        assertEquals(WOOFERS, pet.toModelType());
    }

     */

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_OWNER_NAME, INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidOwnerName_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(INVALID_OWNER_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = OwnerName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_OWNER_NAME, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME,
                VALID_PHONE, null, VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, INVALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_OWNER_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TIMESTAMP, VALID_DEADLINE, VALID_MARK, invalidTags);
        assertThrows(IllegalValueException.class, pet::toModelType);
    }

}
