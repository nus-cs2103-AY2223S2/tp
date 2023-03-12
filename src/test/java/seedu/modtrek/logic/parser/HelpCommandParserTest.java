package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.HelpCommand;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_helpNoArgs_success() {
        assertParseSuccess(parser, "", new HelpCommand(""));
    }

    @Test
    public void parse_helpValidArgs_success() {
        assertParseSuccess(parser, "add", new HelpCommand(AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpInvalidArgs_failure() {
        assertParseFailure(parser, "wrong",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
