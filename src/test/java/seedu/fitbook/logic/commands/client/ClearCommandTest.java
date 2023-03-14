package seedu.fitbook.logic.commands.client;

import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.ClearCommand;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFitBook_success() {
        FitBookModel model = new FitBookModelManager();
        FitBookModel expectedFitBookModel = new FitBookModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS,
                expectedFitBookModel);
    }

    @Test
    public void execute_nonEmptyFitBook_success() {
        FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel.setFitBook(new FitBook());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS,
                expectedFitBookModel);
    }

}
