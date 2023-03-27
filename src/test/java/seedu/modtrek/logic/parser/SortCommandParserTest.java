package seedu.modtrek.logic.parser;

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
    }

    @Test
    public void checkParseFailure() {
        assertParseFailure(parser, "g/", "Invalid format!");
        assertParseFailure(parser, "/g /y", "Invalid format!");
    }
}
