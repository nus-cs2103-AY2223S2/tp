package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.order.DeleteOrderCommand;
import trackr.logic.parser.order.DeleteOrderCommandParser;

//@@author chongweiguan-reused
/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteOrderCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteOrderCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
//@@author changgittyhub
public class DeleteOrderCommandParserTest {

    private DeleteOrderCommandParser parser = new DeleteOrderCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOrderCommand() {
        assertParseSuccess(parser, "1", new DeleteOrderCommand(INDEX_FIRST_OBJECT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE));
    }
    //@@author
}
