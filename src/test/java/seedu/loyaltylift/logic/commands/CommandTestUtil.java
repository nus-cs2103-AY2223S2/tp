package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_CUSTOMER_TYPE;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerNameContainsKeywordsPredicate;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.AddOrderDescriptorBuilder;
import seedu.loyaltylift.testutil.EditCustomerDescriptorBuilder;
import seedu.loyaltylift.testutil.EditOrderDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // customer

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_CUSTOMER_TYPE_ENTERPRISE = "ent";
    public static final String VALID_CUSTOMER_TYPE_INDIVIDUAL = "ind";
    public static final Integer VALID_POINTS_AMY = 100;
    public static final Integer VALID_POINTS_BOB = 200;
    public static final Integer VALID_POINTS_ADD = 1;
    public static final Integer VALID_POINTS_SUBTRACT = -1;
    public static final Integer VALID_CUMULATIVE_POINTS_AMY = 100;
    public static final Integer VALID_CUMULATIVE_POINTS_BOB = 200;
    public static final String VALID_NOTE_AMY = "Amy's Note";
    public static final String VALID_NOTE_BOB = "Bob's Note";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String CUSTOMER_TYPE_DESC_ENTERPRISE = " " + PREFIX_CUSTOMER_TYPE
            + VALID_CUSTOMER_TYPE_ENTERPRISE;
    public static final String CUSTOMER_TYPE_DESC_INDIVIDUAL = " " + PREFIX_CUSTOMER_TYPE
            + VALID_CUSTOMER_TYPE_INDIVIDUAL;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_CUSTOMER_TYPE = " " + PREFIX_CUSTOMER_TYPE + "person"; // not a customer type
    public static final String INVALID_QUANTITY = " " + PREFIX_QUANTITY + "-5"; // only positive integers allowed


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCustomerCommand.EditCustomerDescriptor DESC_AMY;
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_BOB;
    public static final EditOrderCommand.EditOrderDescriptor DESC_ORDER_A;
    public static final EditOrderCommand.EditOrderDescriptor DESC_ORDER_B;
    public static final AddOrderCommand.AddOrderDescriptor DESC_ADD_ORDER_A;
    public static final AddOrderCommand.AddOrderDescriptor DESC_ADD_ORDER_B;




    // order

    public static final String VALID_NAME_A = "Strawberry Shortcake";
    public static final String VALID_NAME_B = "Banana Split";
    public static final String VALID_QUANTITY_A = "5";
    public static final String VALID_QUANTITY_B = "2";
    public static final String VALID_STATUS_A_PENDING_DATE = "2022/01/09";
    public static final String VALID_STATUS_A_PAID_DATE = "2022/03/26";
    public static final String VALID_STATUS_B_PENDING_DATE = "2023/02/09";
    public static final String VALID_ADDRESS_A = "10 Summer Drive, Singapore 3098812";
    public static final String VALID_ADDRESS_B = "11 Fabordrive, Singapore 3001298";
    public static final String VALID_CREATED_DATE_A = "2023/01/09";
    public static final String VALID_CREATED_DATE_B = "2022/12/20";
    public static final String VALID_NOTE_A = "Note A";
    public static final String VALID_NOTE_B = "Note B";

    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
        DESC_ORDER_A = new EditOrderDescriptorBuilder().withName(VALID_NAME_A).withAddress(VALID_ADDRESS_A)
                .withQuantity(VALID_QUANTITY_A).build();
        DESC_ORDER_B = new EditOrderDescriptorBuilder().withName(VALID_NAME_B).withAddress(VALID_ADDRESS_B)
                .withQuantity(VALID_QUANTITY_B).build();
        DESC_ADD_ORDER_A = new AddOrderDescriptorBuilder().withName(VALID_NAME_A).withQuantity(VALID_QUANTITY_A)
                .withAddress(VALID_ADDRESS_A).build();
        DESC_ADD_ORDER_B = new AddOrderDescriptorBuilder().withName(VALID_NAME_B)
                .withQuantity(VALID_QUANTITY_B).build();
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
     * - the address book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new CustomerNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the order at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showOrderAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOrderList().size());

        Order order = model.getFilteredOrderList().get(targetIndex.getZeroBased());
        model.updateFilteredOrderList(a -> a == order);

        assertEquals(1, model.getFilteredOrderList().size());
    }

}
