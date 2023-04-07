package fasttrack.logic.commands.general;

import static fasttrack.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.UserPrefs;
import fasttrack.model.expense.ExpenseContainsKeywordsPredicate;
import fasttrack.testutil.TypicalExpenses;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TypicalExpenses.getTypicalExpenseTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalExpenses.getTypicalExpenseTracker(), new UserPrefs());

    @Test
    public void equals() {
        ExpenseContainsKeywordsPredicate firstPredicate =
                new ExpenseContainsKeywordsPredicate(Collections.singletonList("first"));
        ExpenseContainsKeywordsPredicate secondPredicate =
                new ExpenseContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        ExpenseContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExpensesList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 3);
        ExpenseContainsKeywordsPredicate predicate = preparePredicate("apple banana cherry");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExpensesList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(
                TypicalExpenses.BANANA, TypicalExpenses.APPLE, TypicalExpenses.CHERRY), model.getFilteredExpenseList());
    }

    /**
     * Parses {@code userInput} into a {@code ExpenseContainsKeywordsPredicate}.
     */
    private ExpenseContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ExpenseContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
