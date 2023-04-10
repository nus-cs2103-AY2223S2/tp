package fasttrack.logic.parser;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.general.FindCommand;
import fasttrack.model.expense.ExpenseContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ExpenseContainsKeywordsPredicate(Arrays.asList("Apple", "Orange")));
        assertParseSuccess(parser, "Apple Orange", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Apple \n \t Orange  \t", expectedFindCommand);
    }

}
