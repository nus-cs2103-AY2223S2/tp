package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.module.Code;

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
        assertTrue(Code.isValidCode("peter jack")); // alphabets only
        assertTrue(Code.isValidCode("12345")); // numbers only
        assertTrue(Code.isValidCode("peter the 2nd")); // alphanumeric characters
        assertTrue(Code.isValidCode("Capital Tan")); // with capital letters
        assertTrue(Code.isValidCode("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
