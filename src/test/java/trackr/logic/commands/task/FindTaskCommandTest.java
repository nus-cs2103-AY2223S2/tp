package trackr.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static trackr.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.BUY_EGGS_D;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;
import static trackr.testutil.TypicalTasks.THROW_EXPIRED_N;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.testutil.TaskPredicateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
//@@author liumc-sg-reused
public class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Collections.singletonList("first"))
                .withTaskDeadline("01/01/2023")
                .withTaskStatus("D")
                .build();

        TaskContainsKeywordsPredicate secondPredicate = new TaskPredicateBuilder()
                .withTaskNameKeywords(Collections.singletonList("second"))
                .withTaskDeadline("11/11/2023")
                .withTaskStatus("N")
                .build();

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
    public void execute_zeroTaskNameKeywords_noTaskFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(" ", null, null);
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.TASK);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleTaskNameKeywords_multipleTasksFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Buy Inventory", null, null);
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.TASK);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SORT_INVENTORY_N, BUY_EGGS_D), model.getFilteredTaskList());
    }

    @Test
    public void execute_taskDeadline_noTaskFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(null, "12/12/2012", null);
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.TASK);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_taskDeadline_multipleTasksFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate(null, "01/01/2024", null);
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.TASK);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SORT_INVENTORY_N, THROW_EXPIRED_N), model.getFilteredTaskList());
    }

    @Test
    public void execute_taskStatus_oneTaskFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0,
                ModelEnum.TASK.toString().toLowerCase());
        TaskContainsKeywordsPredicate predicate = preparePredicate(null, null, "N");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.deleteItem(SORT_INVENTORY_N, ModelEnum.TASK);
        model.deleteItem(SORT_INVENTORY_N, ModelEnum.TASK);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.TASK);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_taskStatus_multipleTasksFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate(null, null, "D");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.TASK);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THROW_EXPIRED_N, BUY_EGGS_D), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskNameContainsKeywordsPredicate}.
     */
    private TaskContainsKeywordsPredicate preparePredicate(String nameKeywords, String deadline, String status) {
        return new TaskPredicateBuilder()
                .withTaskNameKeywords(nameKeywords == null ? null : Arrays.asList(nameKeywords.split("\\s+")))
                .withTaskDeadline(deadline)
                .withTaskStatus(status)
                .build();
    }
}
