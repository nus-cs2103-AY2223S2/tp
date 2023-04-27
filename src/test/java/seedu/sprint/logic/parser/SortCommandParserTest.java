package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.commands.SortCommand;
import seedu.sprint.logic.parser.SortCommandParser.SortingOrder;
import seedu.sprint.logic.parser.SortCommandParser.SortingSequence;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_noArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noOrderArg_throwsParseException() {
        assertParseFailure(parser, "a ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noSequenceArg_throwsParseException() {
        assertParseFailure(parser, "alphabetical", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_validArgsAscendingAlphabetical_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand(SortingOrder.ALPHABETICAL, SortingSequence.ASCENDING);
        assertParseSuccess(parser, "a alphabetical", expectedSortCommand);

        // multiple whitespaces between input args
        assertParseSuccess(parser, " a \t alphabetical  \t", expectedSortCommand);
    }

    @Test
    public void parse_validArgsDescendingDeadline_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand(SortingOrder.DEADLINE, SortingSequence.DESCENDING);
        assertParseSuccess(parser, "d deadline", expectedSortCommand);

        // multiple whitespaces between input args
        assertParseSuccess(parser, " d \n deadline \t", expectedSortCommand);
    }
}
