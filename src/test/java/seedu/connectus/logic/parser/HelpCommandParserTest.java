package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_HELP;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_COMMAND_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_ADD;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_ADD_T;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_CHAT;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_CLEAR;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_DELETE;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_DELETE_T;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_EDIT;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_EMPTY;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_EXIT;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_HELP;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_LIST;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_OPEN;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_SEARCH;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_UPCOMING_B;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_WHITESPACE;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_HELP_COMMAND_WITH_WHITESPACE;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.logic.commands.ChatCommand;
import seedu.connectus.logic.commands.ClearCommand;
import seedu.connectus.logic.commands.DeleteCommand;
import seedu.connectus.logic.commands.DeleteTagFromPersonCommand;
import seedu.connectus.logic.commands.EditCommand;
import seedu.connectus.logic.commands.ExitCommand;
import seedu.connectus.logic.commands.HelpCommand;
import seedu.connectus.logic.commands.ListCommand;
import seedu.connectus.logic.commands.OpenCommand;
import seedu.connectus.logic.commands.SearchCommand;
import seedu.connectus.logic.commands.UpcomingBirthdayCommand;


public class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_noArgs_returnsHelpCommandWithNoArgs() {
        assertParseSuccess(parser, VALID_HELP_COMMAND_EMPTY, new HelpCommand());
    }

    @Test
    public void parse_whitespaceOnly_returnsHelpCommandWithNoArgs() {
        assertParseSuccess(parser, VALID_HELP_COMMAND_WHITESPACE, new HelpCommand());
    }

    @Test
    public void parse_validArgs_returnsHelpCommandWithArgs() {
        assertParseSuccess(parser, VALID_HELP_COMMAND_ADD,
                new HelpCommand(AddCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_ADD_T,
                new HelpCommand(AddTagToPersonCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_CLEAR,
                new HelpCommand(ClearCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_DELETE,
                new HelpCommand(DeleteCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_DELETE_T,
                new HelpCommand(DeleteTagFromPersonCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_EDIT,
                new HelpCommand(EditCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_EXIT,
                new HelpCommand(ExitCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_HELP,
                new HelpCommand(HelpCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_LIST,
                new HelpCommand(ListCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_SEARCH,
                new HelpCommand(SearchCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_WITH_WHITESPACE,
                new HelpCommand(ClearCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_CHAT,
                new HelpCommand(ChatCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_OPEN,
                new HelpCommand(OpenCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, VALID_HELP_COMMAND_UPCOMING_B,
                new HelpCommand(UpcomingBirthdayCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseHelp_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_COMMAND_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_HELP, HelpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseHelp_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

}
