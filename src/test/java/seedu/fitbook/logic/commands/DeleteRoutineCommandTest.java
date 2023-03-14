package seedu.fitbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbook.logic.commands.CommandTestUtil.showRoutineAtIndex;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_ROUTINE;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_SECOND_ROUTINE;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.routines.Routine;

/**
 * Contains integration tests (interaction with the FitBookModel) and unit tests for
 * {@code DeleteRoutineCommand}.
 */
public class DeleteRoutineCommandTest {

    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(), getTypicalFitBookExerciseRoutine(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Routine routineToDelete = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getZeroBased());
        DeleteRoutineCommand deleteRoutineCommand = new DeleteRoutineCommand(INDEX_FIRST_ROUTINE);

        String expectedMessage = String.format(DeleteRoutineCommand.MESSAGE_DELETE_ROUTINE_SUCCESS, routineToDelete);

        FitBookModelManager expectedFitBookModel = new FitBookModelManager(model.getFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel.deleteRoutine(routineToDelete);

        assertCommandSuccess(deleteRoutineCommand, model, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoutineList().size() + 1);
        DeleteRoutineCommand deleteRoutineCommand = new DeleteRoutineCommand(outOfBoundIndex);

        assertCommandFailure(deleteRoutineCommand, model, Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRoutineAtIndex(model, INDEX_FIRST_ROUTINE);

        Routine routineToDelete = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getZeroBased());
        DeleteRoutineCommand deleteRoutineCommand = new DeleteRoutineCommand(INDEX_FIRST_ROUTINE);

        String expectedMessage = String.format(DeleteRoutineCommand.MESSAGE_DELETE_ROUTINE_SUCCESS, routineToDelete);

        FitBookModel expectedFitBookModel = new FitBookModelManager(model.getFitBook(),
                getTypicalFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel.deleteRoutine(routineToDelete);
        showNoRoutine(expectedFitBookModel);

        assertCommandSuccess(deleteRoutineCommand, model, expectedMessage, expectedFitBookModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRoutineAtIndex(model, INDEX_FIRST_ROUTINE);

        Index outOfBoundIndex = INDEX_SECOND_ROUTINE;
        // ensures that outOfBoundIndex is still in bounds of FitBook list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitBookExerciseRoutine().getRoutineList().size());

        DeleteRoutineCommand deleteRoutineCommand = new DeleteRoutineCommand(outOfBoundIndex);
        assertCommandFailure(deleteRoutineCommand, model, Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteRoutineCommand deleteFirstRoutineCommand = new DeleteRoutineCommand(INDEX_FIRST_ROUTINE);
        DeleteRoutineCommand deleteSecondRoutineCommand = new DeleteRoutineCommand(INDEX_SECOND_ROUTINE);

        // same object -> returns true
        assertTrue(deleteFirstRoutineCommand.equals(deleteFirstRoutineCommand));

        // same values -> returns true
        DeleteRoutineCommand deleteFirstRoutineCommandCopy = new DeleteRoutineCommand(INDEX_FIRST_ROUTINE);
        assertTrue(deleteFirstRoutineCommand.equals(deleteFirstRoutineCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstRoutineCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstRoutineCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstRoutineCommand.equals(deleteSecondRoutineCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRoutine(FitBookModel model) {
        model.updateFilteredRoutineList(p -> false);

        assertTrue(model.getFilteredRoutineList().isEmpty());
    }
}
