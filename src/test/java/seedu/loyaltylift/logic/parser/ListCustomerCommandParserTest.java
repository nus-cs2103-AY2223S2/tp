package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.ListCustomerCommand;
import seedu.loyaltylift.model.customer.Customer;

public class ListCustomerCommandParserTest {

    private ListCustomerCommandParser parser = new ListCustomerCommandParser();

    @Test
    public void parse_emptyArgs_success() {
        // sort by name
        ListCustomerCommand expectedListCustomerCommand = new ListCustomerCommand();
        assertParseSuccess(parser, "    ", expectedListCustomerCommand);
    }

    @Test
    public void parse_validSortOption_success() {
        ListCustomerCommand expectedListSortNameCustomerCommand =
                new ListCustomerCommand(Customer.SORT_POINTS, PREDICATE_SHOW_ALL_CUSTOMERS);
        assertParseSuccess(parser, " " + PREFIX_SORT + "points", expectedListSortNameCustomerCommand);
    }

    @Test
    public void parser_validFilterOption_success() {
        ListCustomerCommand expectedListFilterMarkedCustomerCommand =
                new ListCustomerCommand(Customer.SORT_NAME, Customer.FILTER_SHOW_MARKED);
        assertParseSuccess(parser, " " + PREFIX_FILTER + "marked", expectedListFilterMarkedCustomerCommand);
    }

    @Test
    public void parse_invalidSortOption_failure() {
        assertParseFailure(parser, " " + PREFIX_SORT + "invalid", ListCustomerCommand.MESSAGE_INVALID_SORT);

        assertParseFailure(parser, " " + PREFIX_FILTER + "invalid", ListCustomerCommand.MESSAGE_INVALID_FILTER);
    }

}
