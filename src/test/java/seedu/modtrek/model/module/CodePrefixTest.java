package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CodePrefixTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Code(null));
    }

    @Test
    public void constructor_invalidCodePrefix_throwsIllegalArgumentException() {
        String invalidCodePrefix = "";
        assertThrows(IllegalArgumentException.class, () -> new CodePrefix(invalidCodePrefix));
    }

    @Test
    public void isValidCodePrefix() {
        // null code prefix
        assertThrows(NullPointerException.class, () -> CodePrefix.isValidCodePrefix(null));

        // invalid code prefixes
        assertFalse(CodePrefix.isValidCodePrefix("")); // empty string
        assertFalse(CodePrefix.isValidCodePrefix(" ")); // spaces only
        assertFalse(CodePrefix.isValidCodePrefix("^")); // only non-alphanumeric characters
        assertFalse(CodePrefix.isValidCodePrefix("peter*")); // contains non-alphanumeric characters

        // valid code prefixes
        assertTrue(CodePrefix.isValidCodePrefix("CS"));
        assertTrue(CodePrefix.isValidCodePrefix("MA"));
        assertTrue(CodePrefix.isValidCodePrefix("ST"));
        assertTrue(CodePrefix.isValidCodePrefix("GEA")); // long prefix
        assertTrue(CodePrefix.isValidCodePrefix("GEC")); // long prefix
        assertTrue(CodePrefix.isValidCodePrefix("GESS")); // longer prefix
    }
}
