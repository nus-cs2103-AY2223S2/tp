package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Position;

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

        // invalid position
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only
        assertFalse(Position.isValidPosition("&")); // only non-alphanumeric characters
        assertFalse(Position.isValidPosition("Data-analyst 10")); // with non-alphanumeric characters and numbers
        assertFalse(Position.isValidPosition("data-analyst")); // with non-alphanumeric characters, but no numbers
        assertFalse(Position.isValidPosition("analyst & engineer")); // non-alphanumeric characters, no numbers


        // valid position
        assertTrue(Position.isValidPosition("Engineer 10")); // with numbers
        assertTrue(Position.isValidPosition("123")); // numbers only
        assertTrue(Position.isValidPosition("analyst")); // alphabets only
        assertTrue(Position.isValidPosition("Intern")); // with capital letters
        assertTrue(Position.isValidPosition("software engineer for integrated processing")); // long positions
    }
}
