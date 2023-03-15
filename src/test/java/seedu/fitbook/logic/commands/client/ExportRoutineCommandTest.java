package seedu.fitbook.logic.commands.client;


import static seedu.fitbook.logic.commands.ExportCommand.MESSAGE_SUCCESS;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.ExportRoutineCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;


class ExportRoutineCommandTest {
    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());
    private FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());
    @Test
    public void execute_exportRoutine_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        CommandTestUtil.assertCommandSuccess(new ExportRoutineCommand(),
                model, expectedCommandResult, expectedFitBookModel);
    }

}

