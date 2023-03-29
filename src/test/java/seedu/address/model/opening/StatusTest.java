package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Status;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid status
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("&")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("123")); // numbers only
        assertFalse(Status.isValidStatus("Did 10")); // with numbers
        assertFalse(Status.isValidStatus("finish")); // alphabets only
        assertFalse(Status.isValidStatus("Finished")); // with capital letters
        assertFalse(Status.isValidStatus("Applied Interviewing")); // multiple valid

        // valid status
        assertTrue(Status.isValidStatus("applied")); // alphabets only
        assertTrue(Status.isValidStatus("Interviewing")); // with capital letters
        assertTrue(Status.isValidStatus("ACCEPTED")); // all capital letters
    }
}
