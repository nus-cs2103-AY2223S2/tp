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
        // null grade
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid grades
        assertFalse(Grade.isValidGrade("A++")); // empty string
        assertFalse(Grade.isValidGrade("B*")); // not supported grade
        assertFalse(Grade.isValidGrade("E")); // between grade D and F
        assertFalse(Grade.isValidGrade("G")); // after grade F
        assertFalse(Grade.isValidGrade("Z")); // largest letter
        assertFalse(Grade.isValidGrade("R")); // before grade S
        assertFalse(Grade.isValidGrade("T")); // after grade S, before grade U
        assertFalse(Grade.isValidGrade("V")); // after grade U
        assertFalse(Grade.isValidGrade("1")); // not a letter
        assertFalse(Grade.isValidGrade("+")); // missing letter
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // valid grades
        assertTrue(Grade.isValidGrade("A+"));
        assertTrue(Grade.isValidGrade("S"));
        assertTrue(Grade.isValidGrade("B-"));
    }

    @Test
    public void testCompareTo() {
        assertTrue(new Grade("A+").compareTo(new Grade("A")) < 0);
        assertTrue(new Grade("B+").compareTo(new Grade("A")) > 0);
        assertTrue(new Grade("B+").compareTo(new Grade("B+")) == 0);
    }
}
