package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.ListCustomerCommand;
import seedu.loyaltylift.model.customer.Customer;

public class ListCustomerCommandParserTest {

    private ListCustomerCommandParser parser = new ListCustomerCommandParser();

    @Test
    public void parse_emptyArgs_returnsListCommand() {
        // sort by name
        ListCustomerCommand expectedListCustomerCommand =
                new ListCustomerCommand(Customer.SORT_NAME);
        assertParseSuccess(parser, "    ", expectedListCustomerCommand);
    }

    @Test
    public void parse_validSortOption_returnsListCommand() {
        ListCustomerCommand expectedListSortNameCustomerCommand =
                new ListCustomerCommand(Customer.SORT_NAME);
        assertParseSuccess(parser, "s/name", expectedListSortNameCustomerCommand);
    }

}
