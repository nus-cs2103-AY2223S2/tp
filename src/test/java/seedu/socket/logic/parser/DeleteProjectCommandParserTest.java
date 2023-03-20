package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.DeleteProjectCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteProjectCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteProjectCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteProjectCommandParserTest {

    private DeleteProjectCommandParser parser = new DeleteProjectCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteProjectCommand() {
        assertParseSuccess(parser, "1", new DeleteProjectCommand(INDEX_FIRST_PROJECT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteProjectCommand.MESSAGE_USAGE));
    }
}
