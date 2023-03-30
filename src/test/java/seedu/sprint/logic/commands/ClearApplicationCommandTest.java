package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.ApplicationModel;
import seedu.sprint.model.ApplicationModelManager;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.UserPrefs;

public class ClearApplicationCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyInternshipBook_success() {
        ApplicationModel model = new ApplicationModelManager();
        ApplicationModel expectedModel = new ApplicationModelManager();
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new ClearApplicationCommand(), model, commandHistory,
                ClearApplicationCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternshipBook_success() {
        ApplicationModel model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
        ApplicationModel expectedModel = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel.setInternshipBook(new InternshipBook());
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new ClearApplicationCommand(), model, commandHistory,
                ClearApplicationCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
