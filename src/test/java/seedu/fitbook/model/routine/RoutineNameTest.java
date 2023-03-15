package seedu.fitbook.model.routine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.routines.RoutineName;

public class RoutineNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoutineName(null));
    }

    @Test
    public void constructor_invalidRoutineName_throwsIllegalArgumentException() {
        String invalidRoutineName = "";
        assertThrows(IllegalArgumentException.class, () -> new RoutineName(invalidRoutineName));
    }

    @Test
    public void isValidRoutineName() {
        // null routine name
        assertThrows(NullPointerException.class, () -> RoutineName.isValidRoutineName(null));

        // invalid routine name
        assertFalse(RoutineName.isValidRoutineName("")); // empty string
        assertFalse(RoutineName.isValidRoutineName(" ")); // spaces only
        assertFalse(RoutineName.isValidRoutineName("^")); // only non-alphanumeric characters
        assertFalse(RoutineName.isValidRoutineName("cardio*")); // contains non-alphanumeric characters

        // valid routine name
        assertTrue(RoutineName.isValidRoutineName("high intense")); // alphabets only
        assertTrue(RoutineName.isValidRoutineName("12345")); // numbers only
        assertTrue(RoutineName.isValidRoutineName("cardio the 2nd")); // alphanumeric characters
        assertTrue(RoutineName.isValidRoutineName("Cardio Routine")); // with capital letters
        assertTrue(RoutineName.isValidRoutineName("High Intensity Interval Training Part 2")); // long names
    }

}
