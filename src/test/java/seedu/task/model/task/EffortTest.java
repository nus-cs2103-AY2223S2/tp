package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.task.model.task.exceptions.InvalidEffortException;

public class EffortTest {

    public static final long VALID_EFFORT_0 = 0;
    public static final long VALID_EFFORT_1 = 1;
    public static final long DEFAULT_EFFORT = 2;
    public static final long VALID_EFFORT_3 = 3;
    public static final long INVALID_EFFORT = -1;

    @Test
    public void constructor_invalidEffort_throwsInvalidEffortException() {
        assertThrows(InvalidEffortException.class, () -> new Effort(INVALID_EFFORT));
    }

    @Test
    public void constructor_createsDefault_returnsTrue() {
        Effort defaultEffort = new Effort();
        Effort expectedEffort = new Effort(DEFAULT_EFFORT);
        assertTrue(defaultEffort.equals(expectedEffort));
    }

    @Test
    public void toString_createsString_equals() {
        Effort effort0 = new Effort(VALID_EFFORT_0);
        Effort effort1 = new Effort(VALID_EFFORT_1);
        Effort effort3 = new Effort(VALID_EFFORT_3);

        assertEquals(effort0.toString(), "0");
        assertEquals(effort1.toString(), "1");
        assertEquals(effort3.toString(), "3");
    }

    @Test
    public void equals() {
        // initialisation
        Effort effort0 = new Effort(VALID_EFFORT_0);
        Effort effort1 = new Effort(VALID_EFFORT_1);
        Effort effort3 = new Effort(VALID_EFFORT_3);

        // same instance -> return true
        assertTrue(effort0.equals(effort0));
        assertTrue(effort1.equals(effort1));

        // different value -> return false
        assertFalse(effort0.equals(effort1));
        assertFalse(effort3.equals(effort1));

        // different instance, same value -> return true
        Effort copy = new Effort(VALID_EFFORT_0);
        assertTrue(copy.equals(effort0));
    }
}
