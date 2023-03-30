package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;

public class ClearCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyInternshipBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new ClearCommand(), model, commandHistory,
                ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternshipBook_success() {
        Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel.setInternshipBook(new InternshipBook());
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new ClearCommand(), model, commandHistory,
                ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
