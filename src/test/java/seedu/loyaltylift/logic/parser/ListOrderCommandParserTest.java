package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.ListOrderCommand;
import seedu.loyaltylift.model.order.Order;

public class ListOrderCommandParserTest {

    private ListOrderCommandParser parser = new ListOrderCommandParser();

    @Test
    public void parse_emptyArgs_returnsListCommand() {
        // sort by name
        ListOrderCommand expectedListOrderCommand =
                new ListOrderCommand(Order.SORT_CREATED_DATE);
        assertParseSuccess(parser, "    ", expectedListOrderCommand);
    }

    @Test
    public void parse_validSortOption_returnsListCommand() {
        ListOrderCommand expectedCommand = new ListOrderCommand(Order.SORT_CREATED_DATE);
        assertParseSuccess(parser, "s/created", expectedCommand);
    }

}
