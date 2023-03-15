package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidMajor_throwsIllegalArgumentException() {
        String invalidMajor = "123";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajor));
    }

    @Test
    public void isValidMajor() {
        // null major
        assertThrows(NullPointerException.class, () -> Major.isValidMajor(null));

        // invalid major
        assertFalse(Major.isValidMajor("12345")); // only numbers in major name
        assertFalse(Major.isValidMajor("Computer23Science")); // numbers in major name
        assertFalse(Major.isValidMajor("Computer_Science")); // underscore in major name
        assertFalse(Major.isValidMajor("Computer @ Science")); // '@' in major name
        assertFalse(Major.isValidMajor("Computer-Science")); // hyphen in major name
        assertFalse(Major.isValidMajor("#Chemistry")); // hashtag in major name
        assertFalse(Major.isValidMajor("Chemistry!")); // exclamation mark in major name
        assertFalse(Major.isValidMajor("Chem^is^try")); // '^' in major name

        // valid major
        assertTrue(Major.isValidMajor("Computer Science")); // normal spelling major name
        assertTrue(Major.isValidMajor("computer science")); // lowercase major name
        assertTrue(Major.isValidMajor("COMPUTER SCIENCE")); // uppercase major name
        assertTrue(Major.isValidMajor("CoMpUteR ScieNCE")); // mixed case major name
        assertTrue(Major.isValidMajor("Chemistry")); // single-word major name
    }
}
