package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrescribeCommandTest {

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrescribeCommand(null, null));
        assertThrows(NullPointerException.class, () -> new PrescribeCommand(null, null));
        assertThrows(NullPointerException.class, () -> new PrescribeCommand(null, null));
    }
}