package seedu.patientist.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class IdNumberTest {
    private static final IdNumber PID1_UPPER = new IdNumber("A12345B");
    private static final IdNumber PID1_LOWER = new IdNumber("a12345b");
    private static final IdNumber PID2_UPPER = new IdNumber("C67890D");
    private static final IdNumber PID2_LOWER = new IdNumber("c67890d");


    @Test
    public void equals_sameId_true() {
        assertTrue(PID1_UPPER.equals(PID1_UPPER));
        assertTrue(PID1_UPPER.equals(new IdNumber("A12345B")));
    }

    @Test
    public void equals_diffId_false() {
        assertFalse(PID1_UPPER.equals(PID2_UPPER));
    }

    @Test
    public void equals_diffCase_true() {
        assertTrue(PID1_UPPER.equals(PID1_LOWER));
        assertTrue(PID2_LOWER.equals(PID2_UPPER));
    }

    @Test
    public void constructor_invalidIdNumberInput() {
        assertThrows(IllegalArgumentException.class, () -> new IdNumber("Invalid as there are spaces"));
    }

    @Test
    public void constructor_nullInput() {
        assertThrows(NullPointerException.class, () -> new IdNumber(null));
    }
}
