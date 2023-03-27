package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.deleteFirstInternship;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.findFirstInternship;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.UserPrefs;


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
