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
        // Nothing yet.
    }

    @Test
    public void constructor_valid_Deadline() {
        assertTrue(new Deadline("2023-03-05") instanceof Deadline);
    }

    @Test
    public void isValidDeadline() {
        // Nothing yet.
    }

}
