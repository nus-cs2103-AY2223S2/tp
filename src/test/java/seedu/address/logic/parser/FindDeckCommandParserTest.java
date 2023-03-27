package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deckcommands.FindDeckCommand;

public class FindDeckCommandParserTest {

    private FindDeckCommandParser parser = new FindDeckCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindDeckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindDeckCommand expectedFindCommand =
                new FindDeckCommand(Arrays.asList("What's", "gravity"));
        assertParseSuccess(parser, "What's gravity", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n What's \n \t gravity  \t", expectedFindCommand);
    }

}
