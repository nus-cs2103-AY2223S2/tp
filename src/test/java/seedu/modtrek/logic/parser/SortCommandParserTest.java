package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.SortCommand;

public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void checkParseSuccess() {
        assertParseSuccess(parser, "/g", new SortCommand("grade"));
        assertParseSuccess(parser, "/y", new SortCommand("year"));
        assertParseSuccess(parser, "/c", new SortCommand("credits"));
        assertParseSuccess(parser, "/m", new SortCommand("code"));
        assertParseSuccess(parser, "/t", new SortCommand("tag"));
    }

    @Test
    public void checkParseFailure() {
        assertParseFailure(parser, "g/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "/g /y", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
