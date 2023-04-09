package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.CARGO_PANTS_O;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;
import static trackr.testutil.TypicalOrders.CUPCAKE_O;
import static trackr.testutil.TypicalOrders.NIKE_CAP_O;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.order.FindOrderCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.order.OrderContainsKeywordsPredicate;
import trackr.testutil.OrderPredicateBuilder;

public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void equals() {
        OrderContainsKeywordsPredicate firstPredicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Collections.singletonList("first"))
                .withOrderDeadline("01/01/2023")
                .withOrderStatus("D")
                .withOrderQuantity("1")
                .withCustomerNameKeywords("Nigel")
                .withCustomerPhone("93847483")
                .withCustomerAddress("13 Countrywalk Road")
                .build();

        OrderContainsKeywordsPredicate secondPredicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Collections.singletonList("second"))
                .withOrderDeadline("11/11/2023")
                .withOrderStatus("N")
                .withOrderQuantity("1")
                .withCustomerNameKeywords("Amy")
                .withCustomerPhone("93842283")
                .withCustomerAddress("12 Fifty Road")
                .build();

        FindOrderCommand findOrderFirstCommand = new FindOrderCommand(firstPredicate);
        FindOrderCommand findOrderSecondCommand = new FindOrderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findOrderFirstCommand.equals(findOrderFirstCommand));

        // same values -> returns true
        FindOrderCommand findOrderFirstCommandCopy = new FindOrderCommand(firstPredicate);
        assertTrue(findOrderFirstCommand.equals(findOrderFirstCommandCopy));

        // different types -> returns false
        assertFalse(findOrderFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findOrderFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findOrderFirstCommand.equals(findOrderSecondCommand));
    }

    @Test
    public void execute_zeroOrderNameKeywords_noOrderFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        OrderContainsKeywordsPredicate predicate = preparePredicate(" ", null, null);
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.ORDER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_multipleOrderNameKeywords_multipleOrdersFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 2);
        OrderContainsKeywordsPredicate predicate = preparePredicate("Chocolate Cargo", null, null);
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.ORDER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHOCOLATE_COOKIES_O, CARGO_PANTS_O), model.getFilteredOrderList());
    }

    @Test
    public void execute_orderDeadline_noOrderFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        OrderContainsKeywordsPredicate predicate = preparePredicate(null, "12/12/2012", null);
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.ORDER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_orderDeadline_multipleOrdersFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 2);
        OrderContainsKeywordsPredicate predicate = preparePredicate(null, "01/01/2024", null);
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.ORDER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHOCOLATE_COOKIES_O, NIKE_CAP_O), model.getFilteredOrderList());
    }

    @Test
    public void execute_orderStatus_noOrderFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        OrderContainsKeywordsPredicate predicate = preparePredicate(null, null, "D");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.deleteItem(CARGO_PANTS_O, ModelEnum.ORDER);
        model.deleteItem(CARGO_PANTS_O, ModelEnum.ORDER);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.ORDER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_orderStatus_multipleOrdersFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 3);
        OrderContainsKeywordsPredicate predicate = preparePredicate(null, null, "N");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredItemList(predicate, ModelEnum.ORDER);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHOCOLATE_COOKIES_O, CUPCAKE_O, NIKE_CAP_O), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code OrderNameContainsKeywordsPredicate}.
     */
    private OrderContainsKeywordsPredicate preparePredicate(String nameKeywords, String deadline, String status) {
        return new OrderPredicateBuilder()
                .withOrderNameKeywords(nameKeywords == null ? null : Arrays.asList(nameKeywords.split("\\s+")))
                .withOrderDeadline(deadline)
                .withOrderStatus(status)
                .build();
    }
}

