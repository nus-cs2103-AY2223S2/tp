package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.InternshipBook;
import seedu.address.model.UserPrefs;

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
