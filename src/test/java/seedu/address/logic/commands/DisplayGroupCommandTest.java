package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DisplayGroupCommandTest {

    private String expectedOutput = "Listed all sessions";

    @Test
    public void checkObject() {
        assertTrue(new DisplayGroupCommand() != new DisplayGroupCommand());
    }

    @Test
    public void execute_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DisplayGroupCommand().execute(null));
    }
}
