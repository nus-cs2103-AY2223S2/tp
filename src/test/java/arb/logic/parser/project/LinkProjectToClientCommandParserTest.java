package arb.logic.parser.project;

import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.LinkProjectToClientCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteProjectCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteProjectCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class LinkProjectToClientCommandParserTest {

    private LinkProjectToClientCommandParser parser = new LinkProjectToClientCommandParser();

    @Test
    public void parse_validArgs_returnsLinkClientToProjectCommand() {
        assertParseSuccess(parser, "1", new LinkProjectToClientCommand(INDEX_FIRST));
    }

    @Test
    public void parse_validFirstArg_returnsLinkClientToProjectCommand() {
        assertParseSuccess(parser, "1 a", new LinkProjectToClientCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                "Invalid index! " + LinkProjectToClientCommand.MESSAGE_USAGE);
    }
}
