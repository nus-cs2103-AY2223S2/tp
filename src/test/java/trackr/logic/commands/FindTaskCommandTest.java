package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalPersons.getTypicalAddressBook;
import static trackr.testutil.TypicalTasks.BUY_EGGS_D;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.task.TaskContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate = new TaskContainsKeywordsPredicate();
        firstPredicate.setTaskNameKeywords(Collections.singletonList("first"));

        TaskContainsKeywordsPredicate secondPredicate = new TaskContainsKeywordsPredicate();
        secondPredicate.setTaskNameKeywords(Collections.singletonList("first"));

        FindTaskCommand findTaskFirstCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand findTaskSecondCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findTaskFirstCommand.equals(findTaskFirstCommand));

        // same values -> returns true
        FindTaskCommand findTaskFirstCommandCopy = new FindTaskCommand(firstPredicate);
        assertTrue(findTaskFirstCommand.equals(findTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(findTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findTaskFirstCommand.equals(findTaskSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Buy Inventory");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SORT_INVENTORY_N, BUY_EGGS_D), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskNameContainsKeywordsPredicate}.
     */
    private TaskContainsKeywordsPredicate preparePredicate(String userInput) {
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setTaskNameKeywords(Arrays.asList(userInput.split("\\s+")));
        return predicate;
    }
}
