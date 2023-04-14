package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ResourceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Resource(null));
    }

    @Test
    public void constructor_invalidType_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Resource(invalidType));
    }

    @Test
    public void isValidType() {
        // null type number
        assertThrows(NullPointerException.class, () -> Resource.isValidResource(null));

        // invalid type numbers
        assertFalse(Resource.isValidResource("")); // empty string
        assertFalse(Resource.isValidResource(" ")); // spaces only
        assertFalse(Resource.isValidResource("\n")); // new line character
        assertFalse(Resource.isValidResource("\t")); // tab character

        // valid type numbers
        assertTrue(Resource.isValidResource("911")); // exactly 3 numbers
        assertTrue(Resource.isValidResource("93121534"));
        assertTrue(Resource.isValidResource("124293842033123")); // long type numbers
        assertTrue(Resource.isValidResource("91")); // less than 3 numbers
        assertTrue(Resource.isValidResource("type")); // non-numeric
        assertTrue(Resource.isValidResource("9011p041")); // alphabets within digits
        assertTrue(Resource.isValidResource("9312 1534")); // spaces within digits
    }
}
