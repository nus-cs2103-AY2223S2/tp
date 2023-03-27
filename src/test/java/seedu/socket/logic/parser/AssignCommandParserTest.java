package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.AssignCommand;

public class AssignCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE);
    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_validArgs_returnsAssignCommand() {
        AssignCommand expectedAssignCommand = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PROJECT);
        assertParseSuccess(parser, "1 1", expectedAssignCommand);
        // Whitespace in front and behind
        assertParseSuccess(parser, "  1 1  ", expectedAssignCommand);
        // mMultiple whitespaces between keywords
        assertParseSuccess(parser, "  \t \n 1   \t  \n 1 \t ", expectedAssignCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        // Empty String
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        // White space only
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
        // Multiple empty lines
        assertParseFailure(parser, "\n", MESSAGE_INVALID_FORMAT);
        // Tab only
        assertParseFailure(parser, "\t", MESSAGE_INVALID_FORMAT);
        // Tabs and Multiple empty lines
        assertParseFailure(parser, "\n\n\t\t", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_incorrectNumberOfArgs_throwsParseException() {
        // Only 1 Argument
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
        // More than 2 Arguments
        assertParseFailure(parser, "1 1 1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // Invalid person index only
        assertParseFailure(parser, "a 1", MESSAGE_INVALID_FORMAT);
        // Invalid project index only
        assertParseFailure(parser, "1 a", MESSAGE_INVALID_FORMAT);
        // Invalid person & project index
        assertParseFailure(parser, "a a", MESSAGE_INVALID_FORMAT);
    }
}
