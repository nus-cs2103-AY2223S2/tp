package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.cardcommands.FindCardsCommand;

public class FindCardsCommandParserTest {
    private FindCardsCommandParser parser = new FindCardsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCardsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCardsCommand expectedFindCardsCommand =
                new FindCardsCommand(Arrays.asList("What's", "gravity"));
        assertParseSuccess(parser, "What's gravity", expectedFindCardsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n What's \n \t gravity  \t", expectedFindCardsCommand);
    }
}
