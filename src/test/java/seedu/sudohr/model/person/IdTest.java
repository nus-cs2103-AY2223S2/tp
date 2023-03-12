package seedu.sudohr.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // null id number
        assertThrows(NullPointerException.class, () -> Id.isValidId((null)));

        // invalid id numbers
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId("  ")); // spaces only
        assertFalse(Id.isValidId("id")); // non-numeric
        assertFalse(Id.isValidId("900l355")); // alphabets within digits
        assertFalse(Id.isValidId("829 92")); // spaces within digits
        assertFalse(Id.isValidId("   892  ")); // trailing and leading spaces
        assertFalse(Id.isValidId("0")); // no non-zero digits
        assertFalse(Id.isValidId("000")); // no non-zero digits

        // valid id numbers
        assertTrue(Id.isValidId("00099233"));
        assertTrue(Id.isValidId("992330000"));
        assertTrue(Id.isValidId("0009923300000"));
        assertTrue(Id.isValidId("9312015034"));
        assertTrue(Id.isValidId("124293842033123")); // long id numbers
    }
}


