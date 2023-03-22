package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommand.
 */
public class UndoCommandTest {

    @Test
    public void execute_takesNoNull() {
        UndoCommand undoCommand = new UndoCommand();
        assertThrows(NullPointerException.class, () -> undoCommand.execute(null));
    }
}
