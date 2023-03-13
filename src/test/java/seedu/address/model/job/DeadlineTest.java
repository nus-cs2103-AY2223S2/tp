package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid phone numbers
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline("2023-10-2a")); // non-numeric
        assertFalse(Deadline.isValidDeadline("2023/10/20")); // "/" instead of "-"
        assertFalse(Deadline.isValidDeadline("2019-10-20")); // past the current date of today

        // valid phone numbers
        assertTrue(Deadline.isValidDeadline("2023-10-20"));
        assertTrue(Deadline.isValidDeadline("2024-05-04"));
        assertTrue(Deadline.isValidDeadline("2023-09-29"));
    }
}
