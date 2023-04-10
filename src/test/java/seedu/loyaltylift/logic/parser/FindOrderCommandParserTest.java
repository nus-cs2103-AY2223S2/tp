package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.loyaltylift.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.FindOrderCommand;
import seedu.loyaltylift.model.order.OrderNameContainsKeywordsPredicate;

public class FindOrderCommandParserTest {

    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindOrderCommand expectedFindOrderCommand =
                new FindOrderCommand(new OrderNameContainsKeywordsPredicate(Arrays.asList("Strawberry", "Shortcake")));
        assertParseSuccess(parser, "Strawberry Shortcake", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Strawberry \n \t Shortcake  \t", expectedFindOrderCommand);
    }

}
