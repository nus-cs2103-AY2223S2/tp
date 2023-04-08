package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_ORDERS_ONLY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_A;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_C;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_D;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.OrderNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderCommand}.
 */
public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        OrderNameContainsKeywordsPredicate firstPredicate =
                new OrderNameContainsKeywordsPredicate(Collections.singletonList("first"));
        OrderNameContainsKeywordsPredicate secondPredicate =
                new OrderNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindOrderCommand findFirstCommand = new FindOrderCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noOrderFound() {
        OrderNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindOrderCommand command = new FindOrderCommand(predicate);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0),
                LIST_ORDERS_ONLY);

        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_multipleKeywords_multipleOrdersFound() {
        OrderNameContainsKeywordsPredicate predicate = preparePredicate("Strawberry Melon");
        FindOrderCommand command = new FindOrderCommand(predicate);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 3),
                LIST_ORDERS_ONLY);

        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        List<Order> expectedList = Arrays.asList(ORDER_A, ORDER_C, ORDER_D);
        expectedList.sort(Order.SORT_NAME);
        assertEquals(expectedList, model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private OrderNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new OrderNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
