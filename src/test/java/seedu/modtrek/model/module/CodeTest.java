package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Code(null));
    }

    @Test
    public void constructor_invalidCode_throwsIllegalArgumentException() {
        String invalidCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Code(invalidCode));
    }

    @Test
    public void isValidCode() {
        // null name
        assertThrows(NullPointerException.class, () -> Code.isValidCode(null));

        // invalid name
        assertFalse(Code.isValidCode("")); // empty string
        assertFalse(Code.isValidCode(" ")); // spaces only
        assertFalse(Code.isValidCode("^")); // only non-alphanumeric characters
        assertFalse(Code.isValidCode("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Code.isValidCode("CS3230"));
        assertTrue(Code.isValidCode("CS3211"));
        assertTrue(Code.isValidCode("CS2105"));
        assertTrue(Code.isValidCode("MA2108"));
        assertTrue(Code.isValidCode("GEC1025")); // long name
        assertTrue(Code.isValidCode("GESS1035")); // longer name
    }
}
