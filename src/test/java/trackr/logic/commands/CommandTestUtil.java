package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.commons.core.index.Index;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.SupplierList;
import trackr.model.order.Order;
import trackr.model.order.OrderContainsKeywordsPredicate;
import trackr.model.order.OrderDescriptor;
import trackr.model.supplier.NameContainsKeywordsPredicate;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDescriptor;
import trackr.testutil.EditSupplierDescriptorBuilder;
import trackr.testutil.OrderDescriptorBuilder;
import trackr.testutil.TaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditSupplierCommand.EditSupplierDescriptor DESC_AMY;
    public static final EditSupplierCommand.EditSupplierDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditSupplierDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    //task fields
    public static final String VALID_TASK_NAME_SORT_INVENTORY = "Sort Inventory";
    public static final String VALID_TASK_NAME_BUY_FLOUR = "Buy Flour";

    public static final String VALID_TASK_DEADLINE_2100 = "01/01/2100";
    public static final String VALID_TASK_DEADLINE_2024 = "01/01/2024";
    public static final String VALID_TASK_STATUS_DONE = "D";
    public static final String VALID_TASK_STATUS_NOT_DONE = "N";

    public static final String TASK_NAME_DESC_SORT_INVENTORY =
            " " + PREFIX_NAME + VALID_TASK_NAME_SORT_INVENTORY;
    public static final String TASK_NAME_DESC_BUY_FLOUR =
            " " + PREFIX_NAME + VALID_TASK_NAME_BUY_FLOUR;
    public static final String TASK_DEADLINE_DESC_2100 =
            " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE_2100;
    public static final String TASK_DEADLINE_DESC_2024 =
            " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE_2024;
    public static final String TASK_STATUS_DESC_DONE =
            " " + PREFIX_STATUS + VALID_TASK_STATUS_DONE;
    public static final String TASK_STATUS_DESC_NOT_DONE =
            " " + PREFIX_STATUS + VALID_TASK_STATUS_NOT_DONE;

    public static final String INVALID_TASK_NAME_DESC =
            " " + PREFIX_NAME + "Buy eggs & flour"; // '&' not allowed in names
    public static final String INVALID_TASK_DEADLINE_DESC =
            " " + PREFIX_DEADLINE + "aa/01/2025"; // alphabets not allowed in deadlines
    public static final String INVALID_TASK_STATUS_DESC =
            " " + PREFIX_STATUS + "d2"; // status can only be d / D / n / N

    public static final TaskDescriptor DESC_SORT_INVENTORY;
    public static final TaskDescriptor DESC_BUY_FLOUR;

    static {
        DESC_SORT_INVENTORY = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();

        DESC_BUY_FLOUR = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
    }

    //order fields
    public static final String VALID_ORDER_NAME_CHOCOLATE_COOKIES = "Chocolate Cookies";
    public static final String VALID_ORDER_NAME_CUPCAKES = "Cupcakes";
    public static final String VALID_ORDER_DEADLINE_2023 = "02/02/2023";
    public static final String VALID_ORDER_DEADLINE_2024 = "01/01/2024";
    public static final String VALID_ORDER_STATUS_DONE = "D";
    public static final String VALID_ORDER_STATUS_NOT_DONE = "N";
    public static final String VALID_ORDER_STATUS_IN_PROGRESS = "I";
    public static final String VALID_ORDER_QUANTITY_ONE = "1";
    public static final String VALID_ORDER_QUANTITY_TWO = "12";
    public static final String VALID_ORDER_QUANTITY_THREE = "123";
    public static final String VALID_CUSTOMER_NAME = "Nigel Wong";
    public static final String VALID_CUSTOMER_PHONE = "91234567";
    public static final String VALID_CUSTOMER_ADDRESS = "Woodlands Street 43";
    public static final String INVALID_ORDER_NAME = "!@#$%CHIPS";
    public static final String INVALID_ORDER_DEADLINE = "01/01/999/INVALID";
    public static final String INVALID_ORDER_STATUS = "T";
    public static final String INVALID_ORDER_QUANTITY = "9999";
    public static final String INVALID_CUSTOMER_NAME = "!@*&#";
    public static final String INVALID_CUSTOMER_PHONE = "912345674554";
    public static final String INVALID_CUSTOMER_ADDRESS = "  ";

    public static final String ORDER_NAME_DESC_CHOCO_COOKIE =
            " " + PREFIX_ORDERNAME + VALID_ORDER_NAME_CHOCOLATE_COOKIES;
    public static final String ORDER_NAME_DESC_CUPCAKES =
            " " + PREFIX_ORDERNAME + VALID_ORDER_NAME_CUPCAKES;
    public static final String ORDER_DEADLINE_DESC_2023 =
            " " + PREFIX_DEADLINE + VALID_ORDER_DEADLINE_2023;
    public static final String ORDER_DEADLINE_DESC_2024 =
            " " + PREFIX_DEADLINE + VALID_ORDER_DEADLINE_2024;
    public static final String ORDER_STATUS_DESC_DONE =
            " " + PREFIX_STATUS + VALID_ORDER_STATUS_DONE;
    public static final String ORDER_STATUS_DESC_NOT_DONE =
            " " + PREFIX_STATUS + VALID_ORDER_STATUS_NOT_DONE;
    public static final String ORDER_STATUS_DESC_IN_PROGRESS =
            " " + PREFIX_STATUS + VALID_ORDER_STATUS_IN_PROGRESS;
    public static final String ORDER_QUANTITY_DESC_ONE =
            " " + PREFIX_ORDERQUANTITY + VALID_ORDER_QUANTITY_ONE;
    public static final String ORDER_QUANTITY_DESC_TWO =
            " " + PREFIX_DEADLINE + VALID_ORDER_QUANTITY_TWO;
    public static final String CUSTOMER_NAME_DESC_NIGEL =
            " " + PREFIX_NAME + VALID_CUSTOMER_NAME;
    public static final String CUSTOMER_PHONE_DESC =
            " " + PREFIX_PHONE + VALID_CUSTOMER_PHONE;
    public static final String CUSTOMER_ADDRESS_DESC =
            " " + PREFIX_ADDRESS + VALID_CUSTOMER_ADDRESS;



    public static final String INVALID_ORDER_NAME_DESC =
            " " + PREFIX_ORDERNAME + INVALID_ORDER_NAME;
    public static final String INVALID_ORDER_DEADLINE_DESC =
            " " + PREFIX_DEADLINE + INVALID_ORDER_DEADLINE;
    public static final String INVALID_ORDER_STATUS_DESC =
            " " + PREFIX_STATUS + INVALID_ORDER_STATUS;
    public static final String INVALID_ORDER_QUANTITY_DESC =
            " " + PREFIX_ORDERQUANTITY + INVALID_ORDER_QUANTITY;
    public static final String INVALID_CUSTOMER_NAME_DESC =
            " " + PREFIX_NAME + INVALID_CUSTOMER_NAME; // quantity cannot be negative
    public static final String INVALID_CUSTOMER_PHONE_DESC =
            " " + PREFIX_PHONE + INVALID_CUSTOMER_PHONE;
    public static final String INVALID_CUSTOMER_ADDRESS_DESC =
            " " + PREFIX_PHONE + INVALID_CUSTOMER_ADDRESS;

    public static final OrderDescriptor DESC_CHOCO_COOKIE;
    public static final OrderDescriptor DESC_CUPCAKE;

    static {
        DESC_CHOCO_COOKIE = new OrderDescriptorBuilder()
                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
                .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
                .withCustomerName(VALID_CUSTOMER_NAME)
                .withCustomerPhone(VALID_CUSTOMER_PHONE)
                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();

        DESC_CUPCAKE = new OrderDescriptorBuilder()
                .withOrderName(VALID_ORDER_NAME_CUPCAKES)
                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
                .withOrderStatus(VALID_ORDER_STATUS_DONE)
                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
                .withCustomerName(VALID_CUSTOMER_NAME)
                .withCustomerPhone(VALID_CUSTOMER_PHONE)
                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
    }
    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the supplier list, filtered supplier list and selected supplier in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        SupplierList expectedAddressBook = new SupplierList(actualModel.getSupplierList());
        List<Supplier> expectedFilteredList = new ArrayList<>(actualModel.getFilteredSupplierList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getSupplierList());
        assertEquals(expectedFilteredList, actualModel.getFilteredSupplierList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the supplier at the given {@code targetIndex} in the
     * {@code model}'s supplier list.
     */
    public static void showSupplierAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredSupplierList().size());

        Supplier supplier = model.getFilteredSupplierList().get(targetIndex.getZeroBased());
        final String[] splitName = supplier.getName().fullName.split("\\s+");
        model.updateFilteredSupplierList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredSupplierList().size());
    }

    /**
     * Updates {@code model}'s filtered task list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTaskName = task.getTaskName().fullTaskName.split("\\s+");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setTaskNameKeywords(Arrays.asList(splitTaskName[0]));
        model.updateFilteredTaskList(predicate);

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered order list to show only the order at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showOrderAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOrderList().size());

        Order task = model.getFilteredOrderList().get(targetIndex.getZeroBased());
        final String[] splitOrderName = task.getOrderName().value.split("\\s+");
        OrderContainsKeywordsPredicate predicate = new OrderContainsKeywordsPredicate();
        predicate.setOrderNameKeywords(Arrays.asList(splitOrderName[0]));
        model.updateFilteredOrderList(predicate);

        assertEquals(1, model.getFilteredOrderList().size());
    }

}
