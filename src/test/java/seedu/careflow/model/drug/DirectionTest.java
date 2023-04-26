package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class DirectionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Direction(null));
    }

    @Test
    public void constructor_invalidDirection_throwsIllegalArgumentException() {
        String invalidDirection = "";
        assertThrows(IllegalArgumentException.class, () -> new Direction(invalidDirection));
    }

    @Test
    public void isValidDirection() {
        // null
        assertThrows(NullPointerException.class, () -> Direction.isValidDirection(null));

        // invalid direction
        assertFalse(Direction.isValidDirection("")); // empty string
        assertFalse(Direction.isValidDirection(" ")); // spaces only
        // long description exceed 1500 char limit
        assertFalse(Direction.isValidDirection(StringUtil.generateRandomString(1501)));

        // valid direction
        assertTrue(Direction.isValidDirection("amoxicillin"));
        assertTrue(Direction.isValidDirection("a")); // one character
        assertTrue(Direction.isValidDirection("description )( &")); // with special character
        // long description with exactly 1500 char
        assertTrue(Direction.isValidDirection("a" + StringUtil.generateRandomString(1499)));
    }

    @Test
    public void equals() {
        Direction direction = new Direction("Taken by mouth (orally)");
        // null -> returns false
        assertFalse(direction.equals(null));

        // same object -> returns true
        assertTrue(direction.equals(direction));

        // same values -> returns true
        assertTrue(direction.equals(new Direction("Taken by mouth (orally)")));

        // different values -> return false
        assertFalse(direction.equals("Applied to the skin (cutaneously) "
                + "for a local (topical) or bodywide (systemic) effect"));
    }
}
