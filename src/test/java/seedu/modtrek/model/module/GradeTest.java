package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        String invalidGrade = " ";
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void isValidGrade() {
        // null address
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid addresses
        assertFalse(Grade.isValidGrade("A++")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // valid addresses
        assertTrue(Grade.isValidGrade("A+"));
        assertTrue(Grade.isValidGrade("S")); // one character
        assertTrue(Grade.isValidGrade("B-")); // long address
    }

    @Test
    public void testCompareTo() {
        assertTrue(new Grade("A+").compareTo(new Grade("A")) < 0);
        assertTrue(new Grade("B+").compareTo(new Grade("A")) > 0);
        assertTrue(new Grade("B+").compareTo(new Grade("B+")) == 0);
    }
}
