package seedu.fitbook.logic.commands.routine;

import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.ClearRoutinesCommand;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;

public class ClearRoutinesCommandTest {

    @Test
    public void execute_emptyFitBook_success() {
        FitBookModel model = new FitBookModelManager();
        FitBookModel expectedFitBookModel = new FitBookModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearRoutinesCommand(), model, ClearRoutinesCommand.MESSAGE_SUCCESS,
                expectedFitBookModel);
    }

    @Test
    public void execute_nonEmptyFitBook_success() {
        FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel.setFitBookExerciseRoutine(new FitBookExerciseRoutine());

        CommandTestUtil.assertCommandSuccess(new ClearRoutinesCommand(), model, ClearRoutinesCommand.MESSAGE_SUCCESS,
                expectedFitBookModel);
    }

}
