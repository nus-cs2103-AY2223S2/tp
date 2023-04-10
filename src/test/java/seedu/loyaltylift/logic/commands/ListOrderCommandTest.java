package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_ORDERS_ONLY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.OrderStatusPredicate;
import seedu.loyaltylift.model.order.StatusValue;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListOrderCommand.
 */
public class ListOrderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ListOrderCommand listCommand = new ListOrderCommand();
        ListOrderCommand listSortStatusCommand = new ListOrderCommand(
                Order.SORT_STATUS, PREDICATE_SHOW_ALL_ORDERS);
        ListOrderCommand listSortCreatedDatePendingCommand = new ListOrderCommand(
                Order.SORT_CREATED_DATE, new OrderStatusPredicate(StatusValue.PENDING));

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // same comparator and predicate -> returns true
        ListOrderCommand listCommandCopy = new ListOrderCommand();
        assertTrue(listCommand.equals(listCommandCopy));

        ListOrderCommand listSortCreatedDatePendingCommandCopy = new ListOrderCommand(
                Order.SORT_CREATED_DATE, new OrderStatusPredicate(StatusValue.PENDING));
        assertTrue(listSortCreatedDatePendingCommand.equals(listSortCreatedDatePendingCommandCopy));

        // different types -> returns false
        assertFalse(listCommand.equals(1));

        // null -> returns false
        assertFalse(listCommand.equals(null));

        // different comparator -> returns false
        assertFalse(listCommand.equals(listSortStatusCommand));

        // different predicate -> returns false
        assertFalse(listCommand.equals(listSortCreatedDatePendingCommand));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(
                ListOrderCommand.MESSAGE_SUCCESS, LIST_ORDERS_ONLY);
        assertCommandSuccess(new ListOrderCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showOrderAtIndex(model, INDEX_FIRST);
        CommandResult expectedCommandResult = new CommandResult(
                ListOrderCommand.MESSAGE_SUCCESS, LIST_ORDERS_ONLY);
        assertCommandSuccess(new ListOrderCommand(), model, expectedCommandResult, expectedModel);
    }
}
