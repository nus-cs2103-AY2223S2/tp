package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.DeleteCommand;
import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.ExitCommand;
import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.commands.HelpCommand;
import seedu.modtrek.logic.commands.ListCommand;
import seedu.modtrek.logic.commands.TagCommand;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_helpNoArgs_success() {
        assertParseSuccess(parser, "", new HelpCommand(""));
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
    public void parse_helpList_success() {
        assertParseSuccess(parser, "list", new HelpCommand(ListCommand.MESSAGE_USAGE));
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
    public void parse_helpInvalidArgs_failure() {
        assertParseFailure(parser, "wrong",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
