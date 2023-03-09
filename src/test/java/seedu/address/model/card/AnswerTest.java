package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Answer(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Answer.isValidAddress(null));

        // invalid addresses
        assertFalse(Answer.isValidAddress("")); // empty string
        assertFalse(Answer.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Answer.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Answer.isValidAddress("-")); // one character
        assertTrue(Answer.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
