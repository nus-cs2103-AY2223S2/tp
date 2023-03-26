package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.FindCustomerCommand;
import seedu.loyaltylift.model.customer.CustomerNameContainsKeywordsPredicate;

public class FindCustomerCommandParserTest {

    private FindCustomerCommandParser parser = new FindCustomerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCustomerCommand expectedFindCustomerCommand =
                new FindCustomerCommand(new CustomerNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCustomerCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCustomerCommand);
    }

}
