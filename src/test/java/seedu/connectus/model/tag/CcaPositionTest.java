package seedu.connectus.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaPositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CcaPosition(null));
    }

    @Test
    public void constructor_invalidCcaPositionName_throwsIllegalArgumentException() {
        String invalidCcaPositionName = "";
        assertThrows(IllegalArgumentException.class, () -> new CcaPosition(invalidCcaPositionName));
    }

    @Test
    public void isValidCcaPositionName_null_throwsNullPointerException() {
        // null CCA Position name
        assertThrows(NullPointerException.class, () -> CcaPosition.isValidCcaPositionName(null));
    }

    @Test
    public void isValidCcaPositionName_invalidCcaPosition_returnsFalse() {
        // invalid CCA Position
        assertFalse(CcaPosition.isValidCcaPositionName("")); // empty string
        assertFalse(CcaPosition.isValidCcaPositionName(" ")); // spaces only
    }

    @Test
    public void isValidCcaPositionName_validCcaPosition_returnsTrue() {
        // valid CCA Position
        assertTrue(CcaPosition.isValidCcaPositionName("We Are Friends 123"));
        assertTrue(CcaPosition.isValidCcaPositionName("1")); // one character
        assertTrue(CcaPosition.isValidCcaPositionName("1 ")); // one character with whitespace
        assertTrue(CcaPosition.isValidCcaPositionName("a very very very very very very "
                + "very very very very long cca position")); // long cca position
    }

}
