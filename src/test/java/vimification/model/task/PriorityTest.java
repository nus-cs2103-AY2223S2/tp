package vimification.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    public void testFromInt() {
        assertEquals(Priority.fromInt(1), Optional.of(Priority.VERY_URGENT));
        assertEquals(Priority.fromInt(2), Optional.of(Priority.URGENT));
        assertEquals(Priority.fromInt(3), Optional.of(Priority.NOT_URGENT));
        assertEquals(Priority.fromInt(0), Optional.of(Priority.UNKNOWN));
    }

    @Test
    public void testAsEnding() {
        assertEquals(Priority.VERY_URGENT.asEnding(), " !!!");
        assertEquals(Priority.URGENT.asEnding(), " !!");
        assertEquals(Priority.NOT_URGENT.asEnding(), " !");
        assertEquals(Priority.UNKNOWN.asEnding(), " .");
    }
}
