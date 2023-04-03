package seedu.quickcontacts.logic.commands;

import static seedu.quickcontacts.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.quickcontacts.testutil.TypicalQuickBooks.getTypicalQuickBook;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyQuickBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyQuickBook_success() {
        Model model = new ModelManager(getTypicalQuickBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalQuickBook(), new UserPrefs());
        expectedModel.setQuickBook(new QuickBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
