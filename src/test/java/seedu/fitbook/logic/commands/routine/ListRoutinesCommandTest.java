package seedu.fitbook.logic.commands.routine;

import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandSuccessForRoutine;
import static seedu.fitbook.logic.commands.CommandTestUtil.showRoutineAtIndex;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_ROUTINE;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.ListRoutinesCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the FitBookModel) and unit tests for ListRoutineCommand.
 */
public class ListRoutinesCommandTest {

    private FitBookModel model;
    private FitBookModel expectedFitBookModel;

    @BeforeEach
    public void setUp() {
        model = new FitBookModelManager(getTypicalFitBook(), getTypicalFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel = new FitBookModelManager(model.getFitBook(), model.getFitBookExerciseRoutine(),
                new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccessForRoutine(new ListRoutinesCommand(), model,
                ListRoutinesCommand.MESSAGE_SUCCESS, expectedFitBookModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showRoutineAtIndex(model, INDEX_FIRST_ROUTINE);
        assertCommandSuccessForRoutine(new ListRoutinesCommand(), model, ListRoutinesCommand.MESSAGE_SUCCESS,
                expectedFitBookModel);
    }
}
