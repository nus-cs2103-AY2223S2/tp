package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null, null));
    }

    @Test
    public void nullDeadline_returnsTrue() {
        assertTrue(() -> Deadline.isValidDeadline(null));
    }

    @Test
    public void equals() {
        LocalDateTime validLocalDateTime = LocalDateTime.MAX;
        String validDesc = "Feed pets";

        Deadline deadline = new Deadline(validDesc, validLocalDateTime);
        Deadline deadlineCopy = new Deadline(validDesc, validLocalDateTime);

        Deadline newDeadline = new Deadline(validDesc, validLocalDateTime.minusDays(1));

        // same object => returns true
        assertEquals(deadline, deadline);
        assertEquals(deadline, deadlineCopy);

        // different object returns false
        assertNotEquals(deadline, newDeadline);
    }
}
