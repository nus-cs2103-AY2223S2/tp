package arb.model.project;

import static arb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void constructor_valid_deadlineObject() {
        assertTrue(new Deadline("2023-03-05") instanceof Deadline);
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadlines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // only whitespace
        assertFalse(Deadline.isValidDeadline("*=")); // only non-alphanumeric characters
        assertFalse(Deadline.isValidDeadline("sky")); // contains string that cannot be parsed to a date and time

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("2023-03-03")); // date only
        assertTrue(Deadline.isValidDeadline("3pm")); // time only
        assertTrue(Deadline.isValidDeadline("3pm 2022-03-03")); // date and time
        assertTrue(Deadline.isValidDeadline("midnight")); // natural language processing for time
        assertTrue(Deadline.isValidDeadline("tomorrow")); // natural language processing for date
        assertTrue(Deadline.isValidDeadline("2pm tomorrow")); // time with natural language processing for date
        assertTrue(Deadline.isValidDeadline("midnight tomorrow")); // natural language processing for date and time
    }

}
