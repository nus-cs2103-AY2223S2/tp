package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {

    private static final String VALID_HEIGHT = "1.75";
    private static final String INVALID_HEIGHT = "1.7501";
    private static final String BLANK_HEIGHT = "";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidHeight_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Height(INVALID_HEIGHT));
    }

    @Test
    public void isValidHeight() {
        // null height
        assertThrows(NullPointerException.class, () -> Height.isValidHeight(null));

        // invalid heights
        assertFalse(Height.isValidHeight("")); // empty string
        assertFalse(Height.isValidHeight(" ")); // spaces only
        assertFalse(Height.isValidHeight("1")); // less than 2 decimal places
        assertFalse(Height.isValidHeight("1.0")); // less than 2 decimal places
        assertFalse(Height.isValidHeight("1.7501")); // more than 2 decimal places
        assertFalse(Height.isValidHeight("1.750.5")); // multiple decimal points
        assertFalse(Height.isValidHeight("1.75a")); // non-numeric characters

        // valid heights
        assertTrue(Height.isValidHeight("1.70"));
        assertTrue(Height.isValidHeight("2.00"));
    }

    @Test
    public void equals() {
        Height height1 = new Height(VALID_HEIGHT);
        Height height2 = new Height(VALID_HEIGHT);

        // same object -> returns true
        assertTrue(height1.equals(height1));

        // same values -> returns true
        assertTrue(height1.equals(height2));

        // different types -> returns false
        assertFalse(height1.equals("1.75"));

        // null -> returns false
        assertFalse(height1.equals(null));
    }

    @Test
    public void toString_validHeight_success() {
        Height height = new Height(VALID_HEIGHT);
        assertEquals(height.toString(), "1.75 m");
    }

    @Test
    public void getValue_validHeight_returnsHeight() {
        Height height = new Height(VALID_HEIGHT);
        assertEquals(height.getValue(), VALID_HEIGHT);
    }

}
