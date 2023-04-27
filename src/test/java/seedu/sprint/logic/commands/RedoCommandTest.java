package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.deleteFirstInternship;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.findFirstInternship;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.undoPreviousCommand;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;

public class RedoCommandTest {
    private Model model;
    private ModelManager expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
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
