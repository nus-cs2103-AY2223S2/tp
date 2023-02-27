package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalFitBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.FitBook;
import seedu.address.model.FitBookModel;
import seedu.address.model.FitBookModelManager;
import seedu.address.model.UserPrefs;

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
