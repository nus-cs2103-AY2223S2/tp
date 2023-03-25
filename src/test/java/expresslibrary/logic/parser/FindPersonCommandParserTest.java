package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import expresslibrary.logic.commands.FindPersonCommand;
import expresslibrary.model.person.NameContainsKeywordsPredicate;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPersonCommand);
    }

}
