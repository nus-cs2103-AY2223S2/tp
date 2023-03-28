package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_returnsListCommand() {
        // no white space
        ListCommand expectedListCommand = new ListCommand("");
        String arg = "";
        assertParseSuccess(parser, arg, expectedListCommand);

        // white space present
        arg = "           ";
        assertParseSuccess(parser, arg, expectedListCommand);
    }

    @Test
    public void parse_pairedArg_returnsFindCommand() {
        // exact
        ListCommand expectedListCommand = new ListCommand("paired");
        String arg = " paired";
        assertParseSuccess(parser, arg, expectedListCommand);

        // multiple white space
        arg = "   paired   ";
        assertParseSuccess(parser, arg, expectedListCommand);

        // case insensitive
        arg = " PAiREd";
        assertParseSuccess(parser, arg, expectedListCommand);
    }

    @Test
    public void parse_unpairedArg_returnsFindCommand() {
        // exact
        ListCommand expectedListCommand = new ListCommand("unpaired");
        String arg = " unpaired";
        assertParseSuccess(parser, arg, expectedListCommand);

        // multiple white space
        arg = "   unpaired   ";
        assertParseSuccess(parser, arg, expectedListCommand);

        // case insensitive
        arg = " uNPAiREd";
        assertParseSuccess(parser, arg, expectedListCommand);
    }

    @Test
    public void parse_invalidArg_failure() {
        // no match
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);
        String arg = " abc123";
        assertParseFailure(parser, arg, expectedMessage);

        // substring
        arg = " paired elderly";
        assertParseFailure(parser, arg, expectedMessage);
    }
}
