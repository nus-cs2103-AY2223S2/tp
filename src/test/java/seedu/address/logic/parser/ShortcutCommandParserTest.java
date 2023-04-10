package seedu.address.logic.parser;

import static seedu.address.logic.commands.ShortcutCommand.MESSAGE_INVALID_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.ShortcutCommandParser.CommandType;

public class ShortcutCommandParserTest {

    private ShortcutCommandParser parser = new ShortcutCommandParser();

    @Test
    public void parse_validArgs_returnsShortcutCommandWithAdd() {
        assertParseSuccess(parser, "add dd", new ShortcutCommand(CommandType.ADD, "dd"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithClear() {
        assertParseSuccess(parser, "clear cl", new ShortcutCommand(CommandType.CLEAR, "cl"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithDelete() {
        assertParseSuccess(parser, "delete dl", new ShortcutCommand(CommandType.DELETE, "dl"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithDeleteTag() {
        assertParseSuccess(parser, "delete_tag dtg", new ShortcutCommand(CommandType.DELETE_TAG, "dtg"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithEdit() {
        assertParseSuccess(parser, "edit et", new ShortcutCommand(CommandType.EDIT, "et"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithExit() {
        assertParseSuccess(parser, "exit et", new ShortcutCommand(CommandType.EXIT, "et"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithExport() {
        assertParseSuccess(parser, "export et", new ShortcutCommand(CommandType.EXPORT, "et"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithFilter() {
        assertParseSuccess(parser, "filter fer", new ShortcutCommand(CommandType.FILTER, "fer"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithFind() {
        assertParseSuccess(parser, "find fd", new ShortcutCommand(CommandType.FIND, "fd"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithHelp() {
        assertParseSuccess(parser, "help he", new ShortcutCommand(CommandType.HELP, "he"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithList() {
        assertParseSuccess(parser, "list lst", new ShortcutCommand(CommandType.LIST, "lst"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithMassOp() {
        assertParseSuccess(parser, "mass ma", new ShortcutCommand(CommandType.MASS_OP, "ma"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithRedo() {
        assertParseSuccess(parser, "redo rd", new ShortcutCommand(CommandType.REDO, "rd"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithShortcut() {
        assertParseSuccess(parser, "shortcut st", new ShortcutCommand(CommandType.SHORTCUT, "st"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithTag() {
        assertParseSuccess(parser, "tag tg", new ShortcutCommand(CommandType.TAG, "tg"));
    }

    @Test
    public void parse_validArgs_returnsShortcutCommandWithUndo() {
        assertParseSuccess(parser, "undo ud", new ShortcutCommand(CommandType.UNDO, "ud"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "eat e", String.format(MESSAGE_INVALID_COMMAND, "eat"));
    }
}
