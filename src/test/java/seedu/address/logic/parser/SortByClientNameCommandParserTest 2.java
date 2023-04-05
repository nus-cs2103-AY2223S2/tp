package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sortcommand.SortByClientNameCommand;

class SortByClientNameCommandParserTest {

    private SortByClientNameCommandParser parser = new SortByClientNameCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortByClientNameCommandInOrder() {
        // no leading and trailing whitespaces
        SortByClientNameCommand expectedCommand =
                new SortByClientNameCommand(true);
        assertParseSuccess(parser, "1", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \t", expectedCommand);
    }

    @Test
    public void parse_validArgs_returnsSortByClientNameCommandReverseOrder() {
        // no leading and trailing whitespaces
        SortByClientNameCommand expectedCommand =
                new SortByClientNameCommand(false);
        assertParseSuccess(parser, "0", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 0 \t", expectedCommand);
    }

    @Test
    public void parse_invalidArgsString_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsDecimal_throwsParseException() {
        assertParseFailure(parser, "3.14",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientNameCommand.MESSAGE_USAGE));
    }
}
