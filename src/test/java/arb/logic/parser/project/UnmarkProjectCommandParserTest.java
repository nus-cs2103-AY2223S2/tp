package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.UnmarkProjectCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnmarkProjectCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnmarkProjectCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnmarkProjectCommandParserTest {

    private UnmarkProjectCommandParser parser = new UnmarkProjectCommandParser();

    @Test
    public void parse_validArgs_returnsMarkProjectCommand() {
        assertParseSuccess(parser, "1", new UnmarkProjectCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkProjectCommand.MESSAGE_USAGE));
    }
}
