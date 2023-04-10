package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.deckcommands.FindDecksCommand;

public class FindDecksCommandParserTest {

    private FindDecksCommandParser parser = new FindDecksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindDecksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindDecksCommand() {
        // no leading and trailing whitespaces
        FindDecksCommand expectedFindDecksCommand =
                new FindDecksCommand(Arrays.asList("science", "programming"));
        assertParseSuccess(parser, "science programming", expectedFindDecksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n science \n \t programming  \t", expectedFindDecksCommand);
    }

}
