package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SemYearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SemYear(null));
    }

    @Test
    public void constructor_invalidSemYear_throwsIllegalArgumentException() {
        String invalidSemYear = "";
        assertThrows(IllegalArgumentException.class, () -> new SemYear(invalidSemYear));
    }

    @Test
    public void isValidSemYear() {
        // null semyear
        assertThrows(NullPointerException.class, () -> SemYear.isValidSemYear(null));

        // blank semyear
        assertFalse(SemYear.isValidSemYear("")); // empty string
        assertFalse(SemYear.isValidSemYear(" ")); // spaces only

        // missing parts
        assertFalse(SemYear.isValidSemYear("Y1")); // missing local part
        assertFalse(SemYear.isValidSemYear("Y2"));
        assertFalse(SemYear.isValidSemYear("S1"));

        // invalid semyear
        assertFalse(SemYear.isValidSemYear("Y1_S1"));
        assertFalse(SemYear.isValidSemYear("Y6S1")); // beyond max year
        assertFalse(SemYear.isValidSemYear("Y1S3")); // beyond max sem
        assertFalse(SemYear.isValidSemYear("Y1ST3")); // beyond max sem (special term)

        // valid semyear
        assertTrue(SemYear.isValidSemYear("Y0S1"));
        assertTrue(SemYear.isValidSemYear("Y0ST1"));
        assertTrue(SemYear.isValidSemYear("Y1S1"));
        assertTrue(SemYear.isValidSemYear("Y1ST1"));
        assertTrue(SemYear.isValidSemYear("Y3S2"));
        assertTrue(SemYear.isValidSemYear("Y4S2"));
        assertTrue(SemYear.isValidSemYear("Y2S1"));
    }

    @Test
    public void testCompareTo() {
        assertTrue(new SemYear("Y2S2").compareTo(new SemYear("Y1S1")) < 0);
        assertTrue(new SemYear("Y1S1").compareTo(new SemYear("Y1S2")) > 0);
        assertTrue(new SemYear("Y1S1").compareTo(new SemYear("Y1S1")) == 0);
    }
}
