package vimification.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void testFromInt() {
        assertEquals(Status.fromInt(1), Optional.of(Status.IN_PROGRESS));
        assertEquals(Status.fromInt(2), Optional.of(Status.COMPLETED));
        assertEquals(Status.fromInt(0), Optional.of(Status.NOT_DONE));
    }
}
