package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.deleteFirstInternship;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.findFirstInternship;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.undoPreviousCommand;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {
    private ApplicationModel model;
    private ApplicationModelManager expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_undoCommandExecutedBefore_success() {
        // set up the model
        deleteFirstInternship(model, commandHistory);
        undoPreviousCommand(model, commandHistory);

        // set up the expected model
        deleteFirstInternship(expectedModel);
        undoPreviousCommand(expectedModel);

        expectedModel.redoInternshipBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory,
                String.format(RedoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);
    }

    @Test
    public void execute_multipleUndoCommandsExecutedBefore_success() {
        // set up the model
        deleteFirstInternship(model, commandHistory);
        deleteFirstInternship(model, commandHistory);
        undoPreviousCommand(model, commandHistory);
        undoPreviousCommand(model, commandHistory);

        // set up the expected model
        deleteFirstInternship(expectedModel);
        deleteFirstInternship(expectedModel);
        undoPreviousCommand(expectedModel);
        undoPreviousCommand(expectedModel);

        expectedModel.redoInternshipBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory,
                String.format(RedoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);

        expectedModel.redoInternshipBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory,
                String.format(RedoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);
    }

    @Test
    public void execute_noUndoCommandExecutedBefore_throwsCommandException() {
        findFirstInternship(model);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_noCommandExecutedBefore_throwsCommandException() {
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
}
