package seedu.modtrek.logic.parser;

import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.DeleteCommand;
import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.ExitCommand;
import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.commands.HelpCommand;
import seedu.modtrek.logic.commands.TagCommand;
import seedu.modtrek.logic.commands.ViewCommand;

public class HelpCommandParserTest {
    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_helpNoArgs_success() {
        assertParseSuccess(parser, "", new HelpCommand(HelpCommand.SHOWING_ALL_MESSAGE_USAGE));
    }

    @Test
    public void parse_helpAdd_success() {
        assertParseSuccess(parser, "add", new HelpCommand(AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpEdit_success() {
        assertParseSuccess(parser, "edit", new HelpCommand(EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpDelete_success() {
        assertParseSuccess(parser, "delete", new HelpCommand(DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpTag_success() {
        assertParseSuccess(parser, "tag", new HelpCommand(TagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpView_success() {
        assertParseSuccess(parser, "view", new HelpCommand(ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpFind_success() {
        assertParseSuccess(parser, "find", new HelpCommand(FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpExit_success() {
        assertParseSuccess(parser, "exit", new HelpCommand(ExitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_helpInvalidArgs_success() {
        assertParseSuccess(parser, "wrong", new HelpCommand(HelpCommand.SHOWING_ALL_MESSAGE_USAGE));
    }
}
