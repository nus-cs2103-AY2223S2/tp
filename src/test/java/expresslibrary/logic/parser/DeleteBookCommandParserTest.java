package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import expresslibrary.logic.commands.DeleteBookCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteBookCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteBookCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteBookCommandParserTest {

    private DeleteBookCommandParser parser = new DeleteBookCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteBookCommand() {
        assertParseSuccess(parser, "1", new DeleteBookCommand(INDEX_FIRST, false));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteBookCommand.MESSAGE_USAGE));
    }
}
