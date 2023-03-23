package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommand.
 */
public class UndoCommandTest {

    private final UndoCommand undoCommand = new UndoCommand();

    @Test
    public void execute_takesNoNull() {
        assertThrows(NullPointerException.class, () -> undoCommand.execute(null));
    }
}
