package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_invalidType_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Type(invalidType));
    }

    @Test
    public void isValidType() {
        // null type number
        assertThrows(NullPointerException.class, () -> Type.isValidType(null));

        // invalid type numbers
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" ")); // spaces only
        assertFalse(Type.isValidType("91")); // less than 3 numbers
        assertFalse(Type.isValidType("type")); // non-numeric
        assertFalse(Type.isValidType("9011p041")); // alphabets within digits
        assertFalse(Type.isValidType("9312 1534")); // spaces within digits

        // valid type numbers
        assertTrue(Type.isValidType("911")); // exactly 3 numbers
        assertTrue(Type.isValidType("93121534"));
        assertTrue(Type.isValidType("124293842033123")); // long type numbers
    }
}
