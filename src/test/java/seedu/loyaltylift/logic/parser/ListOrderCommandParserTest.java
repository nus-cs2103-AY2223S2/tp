package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.ListOrderCommand;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.OrderStatusPredicate;
import seedu.loyaltylift.model.order.StatusValue;

public class ListOrderCommandParserTest {

    private ListOrderCommandParser parser = new ListOrderCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        ListOrderCommand expectedListOrderCommand = new ListOrderCommand();
        assertParseSuccess(parser, "    ", expectedListOrderCommand);
    }

    @Test
    public void parse_validArgs_success() {
        ListOrderCommand expectedCommand;
        OrderStatusPredicate pendingPredicate = new OrderStatusPredicate(StatusValue.PENDING);

        // sort status
        expectedCommand = new ListOrderCommand(Order.SORT_STATUS, PREDICATE_SHOW_ALL_ORDERS);
        assertParseSuccess(parser, " " + PREFIX_SORT + "status", expectedCommand);

        // filter pending
        expectedCommand = new ListOrderCommand(Order.SORT_CREATED_DATE, pendingPredicate);
        assertParseSuccess(parser, " " + PREFIX_FILTER + "pending", expectedCommand);

        // sort status and filter pending
        expectedCommand = new ListOrderCommand(Order.SORT_STATUS, pendingPredicate);
        assertParseSuccess(parser, " " + PREFIX_SORT + "status " + PREFIX_FILTER + "pending", expectedCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {
        // invalid sort
        assertParseFailure(parser, " " + PREFIX_SORT + "invalid", ListOrderCommand.MESSAGE_INVALID_SORT);

        // invalid filter
        assertParseFailure(parser, " " + PREFIX_FILTER + "invalid", ListOrderCommand.MESSAGE_INVALID_FILTER);
    }

}
