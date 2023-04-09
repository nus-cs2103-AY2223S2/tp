package vimification.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    public void testFromInt() {
        assertEquals(Priority.fromInt(1), Priority.VERY_URGENT);
        assertEquals(Priority.fromInt(2), Priority.URGENT);
        assertEquals(Priority.fromInt(3), Priority.NOT_URGENT);
        assertEquals(Priority.fromInt(0), Priority.UNKNOWN);
    }

    @Test
    public void testFromIntException() {
        assertThrows(IllegalArgumentException.class, () -> Priority.fromInt(-1));
        assertThrows(IllegalArgumentException.class, () -> Priority.fromInt(4));
    }

    @Test
    public void testAsEnding() {
        assertEquals(Priority.VERY_URGENT.asEnding(), " !!!");
        assertEquals(Priority.URGENT.asEnding(), " !!");
        assertEquals(Priority.NOT_URGENT.asEnding(), " !");
        assertEquals(Priority.UNKNOWN.asEnding(), " .");
    }
}
