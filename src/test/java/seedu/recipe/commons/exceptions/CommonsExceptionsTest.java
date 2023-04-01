package seedu.recipe.commons.exceptions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommonsExceptionsTest {
    @Test
    public void testDataConversionException_constructor() {
        //Null value
        assertDoesNotThrow(() -> new DataConversionException(null));

        //Construct with test message, test Message and Cause
        String testMessage = "Test message";
        Exception wrappedException = new Exception(testMessage);
        DataConversionException e = new DataConversionException(wrappedException);
        assertEquals("java.lang.Exception: " + testMessage, e.getMessage());
        assertEquals(wrappedException, e.getCause());
    }

    @Test
    public void testIllegalValueException_constructor() {
        String testMessage = "Test message";
        assertEquals(testMessage, new IllegalValueException(testMessage).getMessage());

        Exception wrappedException = new Exception(testMessage);
        assertEquals(testMessage, new IllegalValueException(testMessage, wrappedException).getMessage());
        assertEquals(wrappedException, new IllegalValueException(testMessage, wrappedException).getCause());
    }
}
