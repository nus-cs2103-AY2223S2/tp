package seedu.fitbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.commons.core.Messages.MESSAGE_ROUTINES_LISTED_OVERVIEW;
import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.JUMP;
import static seedu.fitbook.testutil.routine.TypicalRoutines.STRENGTH;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.routines.RoutineNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the FitBookModel) for {@code FindRoutineCommand}.
 */
public class FindRoutineCommandTest {
    private FitBookModel model = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());
    private FitBookModel expectedFitBookModel = new FitBookModelManager(getTypicalFitBook(),
            getTypicalFitBookExerciseRoutine(), new UserPrefs());

    @Test
    public void equals() {
        RoutineNameContainsKeywordsPredicate firstPredicate =
                new RoutineNameContainsKeywordsPredicate(Collections.singletonList("first"));
        RoutineNameContainsKeywordsPredicate secondPredicate =
                new RoutineNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindRoutineCommand findFirstRoutineCommand = new FindRoutineCommand(firstPredicate);
        FindRoutineCommand findSecondRoutineCommand = new FindRoutineCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstRoutineCommand.equals(findFirstRoutineCommand));

        // same values -> returns true
        FindRoutineCommand findFirstRoutineCommandCopy = new FindRoutineCommand(firstPredicate);
        assertTrue(findFirstRoutineCommand.equals(findFirstRoutineCommandCopy));

        // different types -> returns false
        assertFalse(findFirstRoutineCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstRoutineCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstRoutineCommand.equals(findSecondRoutineCommand));
    }

    @Test
    public void execute_zeroKeywords_noRoutineFound() {
        String expectedMessage = String.format(MESSAGE_ROUTINES_LISTED_OVERVIEW, 0);
        RoutineNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindRoutineCommand command = new FindRoutineCommand(predicate);
        expectedFitBookModel.updateFilteredRoutineList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedFitBookModel);
        assertEquals(Collections.emptyList(), model.getFilteredRoutineList());
    }

    @Test
    public void execute_multipleKeywords_multipleRoutinesFound() {
        String expectedMessage = String.format(MESSAGE_ROUTINES_LISTED_OVERVIEW, 2);
        RoutineNameContainsKeywordsPredicate predicate = preparePredicate("Jumps Strength");
        FindRoutineCommand command = new FindRoutineCommand(predicate);
        expectedFitBookModel.updateFilteredRoutineList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedFitBookModel);
        assertEquals(Arrays.asList(JUMP, STRENGTH), model.getFilteredRoutineList());
    }


    /**
     * Parses {@code userInput} into a {@code RoutineNameContainsKeywordsPredicate}.
     */
    private RoutineNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RoutineNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
