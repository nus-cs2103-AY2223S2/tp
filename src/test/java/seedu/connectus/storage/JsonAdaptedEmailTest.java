package seedu.connectus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.person.Email;

public class JsonAdaptedEmailTest {
    @Test
    public void testGetEmail() {
        JsonAdaptedEmail email = new JsonAdaptedEmail("johndoe@example.com");
        assertEquals("johndoe@example.com", email.getEmail());

        JsonAdaptedEmail emailWithoutValue = new JsonAdaptedEmail();
        assertNull(emailWithoutValue.getEmail());
    }

    @Test
    public void toModelType_withValidEmail_returnsOptionalOfEmail() throws IllegalValueException {
        String validEmailString = "johndoe@example.com";
        JsonAdaptedEmail jsonAdaptedEmail = new JsonAdaptedEmail(validEmailString);
        Optional<Email> optionalEmail = jsonAdaptedEmail.toModelType();
        assertTrue(optionalEmail.isPresent());
        assertEquals(validEmailString, optionalEmail.get().getValue());
    }

    @Test
    public void toModelType_withInvalidEmail_throwsIllegalArgumentException() {
        String invalidPhoneString = "johndoe";
        JsonAdaptedEmail jsonAdaptedEmail = new JsonAdaptedEmail(invalidPhoneString);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, jsonAdaptedEmail::toModelType);
    }
}
