package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ultron.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.ultron.logic.commands.FindCommand;
import seedu.ultron.logic.parser.FindCommandParser;
import seedu.ultron.model.opening.CompanyOrPositionContainsKeywordsPredicate;

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
                new FindCommand(new CompanyOrPositionContainsKeywordsPredicate(Arrays.asList("Google", "Shopee")));
        assertParseSuccess(parser, "Google Shopee", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Google \n \t Shopee  \t", expectedFindCommand);
    }

}
