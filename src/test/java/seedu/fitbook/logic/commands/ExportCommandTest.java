package seedu.fitbook.logic.commands;


import static seedu.fitbook.logic.commands.ExportCommand.MESSAGE_SUCCESS;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;


class ExportCommandTest {
    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());
    private FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());
    @Test
    public void execute_export_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        CommandTestUtil.assertCommandSuccess(new ExportCommand(), model, expectedCommandResult, expectedFitBookModel);
    }

}
