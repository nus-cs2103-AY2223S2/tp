package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.CUSTOMER_ADDRESS_DESC;
import static trackr.logic.commands.CommandTestUtil.CUSTOMER_NAME_DESC_NIGEL;
import static trackr.logic.commands.CommandTestUtil.CUSTOMER_PHONE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_DEADLINE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_NAME_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_ORDER_STATUS_DESC;
import static trackr.logic.commands.CommandTestUtil.ORDER_DEADLINE_DESC_2023;
import static trackr.logic.commands.CommandTestUtil.ORDER_DEADLINE_DESC_2024;
import static trackr.logic.commands.CommandTestUtil.ORDER_NAME_DESC_CHOCO_COOKIE;
import static trackr.logic.commands.CommandTestUtil.ORDER_NAME_DESC_CUPCAKES;
import static trackr.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_ONE;
import static trackr.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_TWO;
import static trackr.logic.commands.CommandTestUtil.ORDER_STATUS_DESC_DONE;
import static trackr.logic.commands.CommandTestUtil.ORDER_STATUS_DESC_IN_PROGRESS;
import static trackr.logic.commands.CommandTestUtil.ORDER_STATUS_DESC_NOT_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_ADDRESS;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_NAME;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_PHONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2023;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2024;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CUPCAKES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_ONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_TWO;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_IN_PROGRESS;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.order.FindOrderCommand;
import trackr.logic.parser.order.FindOrderCommandParser;
import trackr.model.commons.Deadline;
import trackr.model.order.OrderContainsKeywordsPredicate;
import trackr.model.order.OrderStatus;
import trackr.testutil.OrderPredicateBuilder;

public class FindOrderCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE);
    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid order deadline
        assertParseFailure(parser, INVALID_ORDER_DEADLINE_DESC, String.format(Deadline.MESSAGE_CONSTRAINTS, "Order"));

        // Invalid order status
        assertParseFailure(parser, INVALID_ORDER_STATUS_DESC, OrderStatus.MESSAGE_CONSTRAINTS);

        // Invalid order deadline followed by valid order status
        assertParseFailure(
                parser,
                INVALID_ORDER_DEADLINE_DESC + VALID_ORDER_STATUS_DONE,
                String.format(Deadline.MESSAGE_CONSTRAINTS, "Order")
        );

        // Invalid order status followed by valid order id
        assertParseFailure(
                parser,
                INVALID_ORDER_STATUS_DESC + INVALID_ORDER_NAME_DESC,
                OrderStatus.MESSAGE_CONSTRAINTS
        );

        // valid value followed by invalid value.
        // The test case for invalid value followed by valid value
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}

        assertParseFailure(parser, ORDER_DEADLINE_DESC_2023 + INVALID_ORDER_DEADLINE_DESC,
                String.format(Deadline.MESSAGE_CONSTRAINTS, "Order")); //order deadline

        assertParseFailure(parser, ORDER_STATUS_DESC_NOT_DONE + INVALID_ORDER_STATUS_DESC,
                OrderStatus.MESSAGE_CONSTRAINTS); //order status

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_ORDER_NAME_DESC + INVALID_ORDER_DEADLINE_DESC + VALID_ORDER_STATUS_DONE,
                String.format(Deadline.MESSAGE_CONSTRAINTS, "Order")); //order deadline
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // no leading and trailing whitespaces
        String userInput = ORDER_NAME_DESC_CHOCO_COOKIE + ORDER_DEADLINE_DESC_2024
                + ORDER_STATUS_DESC_NOT_DONE + ORDER_QUANTITY_DESC_TWO
                + CUSTOMER_NAME_DESC_NIGEL + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC;

        OrderContainsKeywordsPredicate predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
                .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
                .withOrderQuantity(VALID_ORDER_QUANTITY_TWO)
                .withCustomerNameKeywords(VALID_CUSTOMER_NAME)
                .withCustomerPhone(VALID_CUSTOMER_PHONE)
                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
        FindOrderCommand expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_someFieldsSpecified_success() {
        // name keywords and deadline
        String userInput = ORDER_NAME_DESC_CHOCO_COOKIE + ORDER_DEADLINE_DESC_2023;

        OrderContainsKeywordsPredicate predicate =
                new OrderPredicateBuilder()
                        .withOrderNameKeywords(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
                        .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
                        .build();
        FindOrderCommand expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name keywords and status
        userInput = ORDER_NAME_DESC_CUPCAKES + ORDER_STATUS_DESC_NOT_DONE;
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
                .build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline and status
        userInput = ORDER_DEADLINE_DESC_2024 + ORDER_STATUS_DESC_NOT_DONE;
        predicate = new OrderPredicateBuilder()
                .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
                .build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // order name keywords
        String userInput = ORDER_NAME_DESC_CHOCO_COOKIE;
        OrderContainsKeywordsPredicate predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(VALID_ORDER_NAME_CHOCOLATE_COOKIES).build();
        FindOrderCommand expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order deadline
        userInput = ORDER_DEADLINE_DESC_2024;
        predicate = new OrderPredicateBuilder().withOrderDeadline(VALID_ORDER_DEADLINE_2024).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order status
        userInput = ORDER_STATUS_DESC_NOT_DONE;
        predicate = new OrderPredicateBuilder().withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order quantity
        userInput = ORDER_QUANTITY_DESC_ONE;
        predicate = new OrderPredicateBuilder().withOrderQuantity(VALID_ORDER_QUANTITY_ONE).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // customer name
        userInput = CUSTOMER_NAME_DESC_NIGEL;
        predicate = new OrderPredicateBuilder().withCustomerNameKeywords(VALID_CUSTOMER_NAME).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // customer name
        userInput = CUSTOMER_PHONE_DESC;
        predicate = new OrderPredicateBuilder().withCustomerPhone(VALID_CUSTOMER_PHONE).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // customer name
        userInput = CUSTOMER_ADDRESS_DESC;
        predicate = new OrderPredicateBuilder().withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = ORDER_NAME_DESC_CHOCO_COOKIE
                + ORDER_DEADLINE_DESC_2024 + ORDER_STATUS_DESC_NOT_DONE
                + ORDER_NAME_DESC_CHOCO_COOKIE + ORDER_DEADLINE_DESC_2024
                + ORDER_STATUS_DESC_NOT_DONE + ORDER_NAME_DESC_CUPCAKES
                + ORDER_DEADLINE_DESC_2023 + ORDER_STATUS_DESC_DONE + ORDER_QUANTITY_DESC_TWO
                + CUSTOMER_NAME_DESC_NIGEL + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC;

        OrderContainsKeywordsPredicate predicate =
                new OrderPredicateBuilder()
                        .withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES)
                        .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
                        .withOrderStatus(VALID_ORDER_STATUS_DONE)
                        .withOrderQuantity(VALID_ORDER_QUANTITY_TWO)
                        .withCustomerNameKeywords(VALID_CUSTOMER_NAME)
                        .withCustomerPhone(VALID_CUSTOMER_PHONE)
                        .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();

        FindOrderCommand expectedCommand = new FindOrderCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        // invalid order name keywords followed by valid order name keywords
        String userInput = INVALID_ORDER_NAME_DESC + ORDER_NAME_DESC_CUPCAKES;
        OrderContainsKeywordsPredicate predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES).build();
        FindOrderCommand expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid order deadline followed by valid order deadline
        userInput = INVALID_ORDER_DEADLINE_DESC + ORDER_DEADLINE_DESC_2023;
        predicate = new OrderPredicateBuilder().withOrderDeadline(VALID_ORDER_DEADLINE_2023).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid order status followed by valid order status
        userInput = INVALID_ORDER_STATUS_DESC + ORDER_STATUS_DESC_IN_PROGRESS;
        predicate = new OrderPredicateBuilder().withOrderStatus(VALID_ORDER_STATUS_IN_PROGRESS).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        // invalid order name keywords followed by valid order name keywords
        userInput = INVALID_ORDER_NAME_DESC + ORDER_NAME_DESC_CUPCAKES;
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid order deadline followed by valid order deadline
        userInput = ORDER_NAME_DESC_CUPCAKES + INVALID_ORDER_DEADLINE_DESC
                + ORDER_STATUS_DESC_IN_PROGRESS + ORDER_DEADLINE_DESC_2023;
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES)
                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
                .withOrderStatus(VALID_ORDER_STATUS_IN_PROGRESS).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid order status followed by valid order status
        userInput = INVALID_ORDER_STATUS_DESC + ORDER_STATUS_DESC_IN_PROGRESS;
        predicate = new OrderPredicateBuilder()
                .withOrderStatus(VALID_ORDER_STATUS_IN_PROGRESS).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_differentInputOrder_success() {
        //order status, order deadline, order name
        String userInput = ORDER_STATUS_DESC_DONE + ORDER_DEADLINE_DESC_2023 + ORDER_NAME_DESC_CUPCAKES
                + ORDER_QUANTITY_DESC_ONE + CUSTOMER_NAME_DESC_NIGEL
                + CUSTOMER_PHONE_DESC + CUSTOMER_ADDRESS_DESC;
        OrderContainsKeywordsPredicate predicate = new OrderPredicateBuilder()
                .withOrderStatus(VALID_ORDER_STATUS_DONE)
                .withOrderDeadline(VALID_ORDER_DEADLINE_2023)
                .withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES)
                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
                .withCustomerNameKeywords(VALID_CUSTOMER_NAME)
                .withCustomerPhone(VALID_CUSTOMER_PHONE)
                .withCustomerAddress(VALID_CUSTOMER_ADDRESS).build();
        FindOrderCommand expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

        //order deadline, order status, order name
        userInput = ORDER_DEADLINE_DESC_2024 + ORDER_STATUS_DESC_NOT_DONE
                + ORDER_NAME_DESC_CUPCAKES + ORDER_QUANTITY_DESC_ONE;
        predicate = new OrderPredicateBuilder()
                .withOrderDeadline(VALID_ORDER_DEADLINE_2024)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE)
                .withOrderNameKeywords(VALID_ORDER_NAME_CUPCAKES)
                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE).build();
        expectedCommand = new FindOrderCommand(predicate);
        assertParseSuccess(parser, userInput, expectedCommand);

    }
}



