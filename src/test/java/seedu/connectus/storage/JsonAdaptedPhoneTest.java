package seedu.connectus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.person.Phone;

public class JsonAdaptedPhoneTest {
    @Test
    public void testGetPhoneSingapore() {
        JsonAdaptedPhone phone = new JsonAdaptedPhone("91234567");
        assertEquals("91234567", phone.getPhone());

        JsonAdaptedPhone phoneWithoutValue = new JsonAdaptedPhone();
        assertNull(phoneWithoutValue.getPhone());
    }

    @Test
    public void testGetPhoneForeign() {
        JsonAdaptedPhone phone = new JsonAdaptedPhone("60123456789");
        assertEquals("60123456789", phone.getPhone());

        JsonAdaptedPhone phoneWithoutValue = new JsonAdaptedPhone();
        assertNull(phoneWithoutValue.getPhone());
    }

    @Test
    public void testGetPhoneShort() {
        JsonAdaptedPhone phone = new JsonAdaptedPhone("912");
        assertEquals("912", phone.getPhone());

        JsonAdaptedPhone phoneWithoutValue = new JsonAdaptedPhone();
        assertNull(phoneWithoutValue.getPhone());
    }

    @Test
    public void toModelType_withValidPhoneSingapore_returnsOptionalOfPhone() throws IllegalValueException {
        String validPhoneString = "91234567";
        JsonAdaptedPhone jsonAdaptedPhone = new JsonAdaptedPhone(validPhoneString);
        Optional<Phone> optionalPhone = jsonAdaptedPhone.toModelType();
        assertTrue(optionalPhone.isPresent());
        assertEquals(validPhoneString, optionalPhone.get().getValue());
    }

    @Test
    public void toModelType_withValidPhoneForeign_returnsOptionalOfPhone() throws IllegalValueException {
        String validPhoneString = "60123456789";
        JsonAdaptedPhone jsonAdaptedPhone = new JsonAdaptedPhone(validPhoneString);
        Optional<Phone> optionalPhone = jsonAdaptedPhone.toModelType();
        assertTrue(optionalPhone.isPresent());
        assertEquals(validPhoneString, optionalPhone.get().getValue());
    }

    @Test
    public void toModelType_withValidPhoneShort_returnsOptionalOfPhone() throws IllegalValueException {
        String validPhoneString = "912";
        JsonAdaptedPhone jsonAdaptedPhone = new JsonAdaptedPhone(validPhoneString);
        Optional<Phone> optionalPhone = jsonAdaptedPhone.toModelType();
        assertTrue(optionalPhone.isPresent());
        assertEquals(validPhoneString, optionalPhone.get().getValue());
    }

    @Test
    public void toModelType_withInvalidPhone_throwsIllegalArgumentException() {
        String invalidPhoneString = "91";
        JsonAdaptedPhone jsonAdaptedPhone = new JsonAdaptedPhone(invalidPhoneString);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, jsonAdaptedPhone::toModelType);
    }
}
