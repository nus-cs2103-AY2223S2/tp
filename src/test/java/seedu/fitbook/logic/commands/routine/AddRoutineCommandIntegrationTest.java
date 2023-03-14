package seedu.fitbook.logic.commands.routine;

import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.AddRoutineCommand;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.testutil.routine.RoutineBuilder;

/**
 * Contains integration tests (interaction with the FitBookModel) for {@code AddCommand}.
 */
public class AddRoutineCommandIntegrationTest {

    private FitBookModel model;

    @BeforeEach
    public void setUp() {
        model = new FitBookModelManager(getTypicalFitBook(), getTypicalFitBookExerciseRoutine(), new UserPrefs());
    }

    @Test
    public void execute_newRoutine_success() {
        Routine validRoutine = new RoutineBuilder().build();

        FitBookModel expectedFitBookModel =
                new FitBookModelManager(model.getFitBook(), model.getFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel.addRoutine(validRoutine);

        CommandTestUtil.assertCommandSuccess(new AddRoutineCommand(validRoutine), model,
                String.format(AddRoutineCommand.MESSAGE_SUCCESS, validRoutine), expectedFitBookModel);
    }

    @Test
    public void execute_duplicateRoutine_throwsCommandException() {
        Routine routineInList = model.getFitBookExerciseRoutine().getRoutineList().get(0);
        assertCommandFailure(new AddRoutineCommand(routineInList), model, AddRoutineCommand.MESSAGE_DUPLICATE_ROUTINE);
    }

}
