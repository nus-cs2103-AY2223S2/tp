package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RedoCommand.
 */
public class RedoCommandTest {

    @Test
    public void execute_takesNoNull() {
        RedoCommand redoCommand = new RedoCommand();
        assertThrows(NullPointerException.class, () -> redoCommand.execute(null));
    }
}
