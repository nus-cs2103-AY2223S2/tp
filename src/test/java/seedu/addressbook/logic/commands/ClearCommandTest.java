package seedu.addressbook.logic.commands;

import static seedu.addressbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.addressbook.testutil.TypicalClients.getTypicalFitBook;

import org.junit.jupiter.api.Test;

import seedu.addressbook.model.FitBook;
import seedu.addressbook.model.FitBookModel;
import seedu.addressbook.model.FitBookModelManager;
import seedu.addressbook.model.UserPrefs;

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
