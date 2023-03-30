package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sortcommand.SortByClientPhoneCommand;

class SortByClientPhoneCommandParserTest {

    private SortByClientPhoneCommandParser parser = new SortByClientPhoneCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientPhoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortByClientPhoneCommandInOrder() {
        // no leading and trailing whitespaces
        SortByClientPhoneCommand expectedCommand =
                new SortByClientPhoneCommand(true);
        assertParseSuccess(parser, "1", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \t", expectedCommand);
    }

    @Test
    public void parse_validArgs_returnsSortByClientPhoneCommandReverseOrder() {
        // no leading and trailing whitespaces
        SortByClientPhoneCommand expectedCommand =
                new SortByClientPhoneCommand(false);
        assertParseSuccess(parser, "0", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 0 \t", expectedCommand);
    }

    @Test
    public void parse_invalidArgsString_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientPhoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsDecimal_throwsParseException() {
        assertParseFailure(parser, "3.14",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortByClientPhoneCommand.MESSAGE_USAGE));
    }
}
