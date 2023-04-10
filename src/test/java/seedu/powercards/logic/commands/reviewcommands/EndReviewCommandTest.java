package seedu.powercards.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;

public class EndReviewCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_endReview_success() {
        CommandResult expectedCommandResult = new CommandResult(
                EndReviewCommand.MESSAGE_SUCCESS,
                false, false, false, true, false, false, false, false, false, false);
        CommandResult actualCommandResult = new EndReviewCommand().execute(model);

        assertEquals(expectedCommandResult, actualCommandResult);
        assertEquals(expectedModel, model);
    }

    @Test
    public void equals_test() {
        EndReviewCommand command = new EndReviewCommand();

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new EndReviewCommand()));

        // different types -> returns false
        assertFalse(command.equals(0));

        // null -> returns false
        assertFalse(command.equals(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EndReviewCommand().execute(null));
    }

}
