package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidPosition_throwsIllegalArgumentException() {
        String invalidPosition = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPosition));
    }

    @Test
    public void isValidPosition() {
        // null position
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid positions
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only
        assertFalse(Position.isValidPosition("%^&*")); // non-alphanumerical numbers

        // valid positions
        assertTrue(Position.isValidPosition("Software Engineering"));
        assertTrue(Position.isValidPosition("s")); // one character
    }
}
