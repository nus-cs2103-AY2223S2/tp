package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deckcommands.FindDecksCommand;

public class FindDecksCommandParserTest {

    private FindDecksCommandParser parser = new FindDecksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindDecksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindDecksCommand expectedFindCommand =
                new FindDecksCommand(Arrays.asList("What's", "gravity"));
        assertParseSuccess(parser, "What's gravity", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n What's \n \t gravity  \t", expectedFindCommand);
    }

}
