package vimification.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void testFromInt() {
        assertEquals(Status.fromInt(1), Status.IN_PROGRESS);
        assertEquals(Status.fromInt(2), Status.COMPLETED);
        assertEquals(Status.fromInt(3), Status.OVERDUE);
        assertEquals(Status.fromInt(0), Status.NOT_DONE);
    }

    @Test
    public void testFromIntException() {
        assertThrows(IllegalArgumentException.class, () -> Status.fromInt(-1));
        assertThrows(IllegalArgumentException.class, () -> Status.fromInt(4));
    }
}
