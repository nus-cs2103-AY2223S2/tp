package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.deleteFirstInternship;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.findFirstInternship;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.ApplicationModel;
import seedu.sprint.model.ApplicationModelManager;
import seedu.sprint.model.UserPrefs;


public class UndoCommandTest {
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
    public void execute_modifyCommandExecutedBefore_success() {
        // set up test model
        deleteFirstInternship(model, commandHistory);

        // set up expected model
        deleteFirstInternship(expectedModel);

        expectedModel.undoInternshipBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
                String.format(UndoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);
    }

    @Test
    public void execute_multipleModifyCommandsExecutedBefore_success() {
        // set up test model
        deleteFirstInternship(model, commandHistory);
        deleteFirstInternship(model, commandHistory);

        // set up expected model
        deleteFirstInternship(expectedModel);
        deleteFirstInternship(expectedModel);

        expectedModel.undoInternshipBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
                String.format(UndoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);

        expectedModel.undoInternshipBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory,
                String.format(UndoCommand.MESSAGE_SUCCESS, "delete 1"), expectedModel);
    }

    @Test
    public void execute_noModifyCommandExecutedBefore_throwsCommandException() {
        findFirstInternship(model);
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_noCommandExecutedBefore_throwsCommandException() {
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }
}
