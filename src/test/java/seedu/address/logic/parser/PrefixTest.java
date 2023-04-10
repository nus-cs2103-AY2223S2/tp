package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PrefixTest {
    @Test
    public void constructor_validPrefix_noExceptionThrown() {
        validateConstructor(""); // empty string
        validateConstructor("/a"); // slash with single lower case alphabetical character
        validateConstructor("/A"); // slash with single upper case alphabetical character
        validateConstructor("/abc"); // slash with multiple lower case alphabetical character
        validateConstructor("/ABC"); // slash with multiple upper case alphabetical character
        validateConstructor("/aBc"); // slash with mix of lower and upper case alphabetical character
    }

    @Test
    public void constructor_null_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new Prefix(null));
    }

    @Test
    public void constructor_invalidPrefix_assertionError() {
        // slash with no alphabetical characters
        assertThrows(AssertionError.class, () -> new Prefix("/"));
        assertThrows(AssertionError.class, () -> new Prefix(" /"));
        assertThrows(AssertionError.class, () -> new Prefix("/ "));

        // slash with whitespace and alphabetical characters
        assertThrows(AssertionError.class, () -> new Prefix("/ aB"));
        assertThrows(AssertionError.class, () -> new Prefix("/a B"));

        // slash with non-alphabetical characters and alphabetical characters
        assertThrows(AssertionError.class, () -> new Prefix("/^aB"));
        assertThrows(AssertionError.class, () -> new Prefix("/a^B"));
        assertThrows(AssertionError.class, () -> new Prefix("/1aB"));
        assertThrows(AssertionError.class, () -> new Prefix("/a1B"));
    }

    @Test
    public void equals() {
        Prefix slashA = new Prefix("/a");

        // same values -> returns true
        Prefix slashACopy = new Prefix("/a");
        assertTrue(slashA.equals(slashACopy));

        // same object -> returns true
        assertTrue(slashA.equals(slashA));

        // null -> returns false
        assertFalse(slashA.equals(null));

        // different type -> returns false
        assertFalse(slashA.equals(1));

        // different prefix -> returns false
        Prefix slashB = new Prefix("/b");
        assertFalse(slashA.equals(slashB));
    }

    private void validateConstructor(String testStr) {
        Prefix prefix = new Prefix(testStr);
        assertEquals(prefix.getPrefix(), testStr);
    }
}
