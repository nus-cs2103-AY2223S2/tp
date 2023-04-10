package seedu.address.model.event.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DescriptionTest {
    private static final String VALID_DESCRIPTION = "Hello world!";

    @Test
    void getDescription() {
        assertEquals(VALID_DESCRIPTION, new Description(VALID_DESCRIPTION).getDescription());
    }

    @Test
    void isValidDescription_valid_returnsTrue() {
        assertTrue(Description.isValidDescription(VALID_DESCRIPTION));
        assertTrue(Description.isValidDescription(VALID_DESCRIPTION + " "));
        assertTrue(Description.isValidDescription(" " + VALID_DESCRIPTION));
        assertTrue(Description.isValidDescription("a"));
    }

    @Test
    void isValidDescription_invalid_returnsFalse() {
        assertFalse(Description.isValidDescription(""));
        assertFalse(Description.isValidDescription(" "));
        assertFalse(Description.isValidDescription("\t"));
        assertFalse(Description.isValidDescription("\n"));
        assertFalse(Description.isValidDescription(" \t\n"));
    }

    @Test
    void testToString() {
        assertEquals(VALID_DESCRIPTION, new Description(VALID_DESCRIPTION).toString());
    }

    @Test
    void testEquals() {
        assertEquals(new Description(VALID_DESCRIPTION), new Description(VALID_DESCRIPTION));
        assertNotEquals(new Description("Goodbye world!"), new Description(VALID_DESCRIPTION));
        assertNotEquals(null, new Description(VALID_DESCRIPTION));
    }
}
