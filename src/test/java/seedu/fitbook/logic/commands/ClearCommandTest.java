package seedu.fitbook.logic.commands;

import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbook.testutil.TypicalClients.getTypicalFitBook;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFitBook_success() {
        FitBookModel model = new FitBookModelManager();
        FitBookModel expectedFitBookModel = new FitBookModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedFitBookModel);
    }

    @Test
    public void execute_nonEmptyFitBook_success() {
        FitBookModel model = new FitBookModelManager(getTypicalFitBook(), new UserPrefs());
        FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(), new UserPrefs());
        expectedFitBookModel.setFitBook(new FitBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedFitBookModel);
    }

}
