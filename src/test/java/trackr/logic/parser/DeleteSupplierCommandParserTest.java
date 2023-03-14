package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_SUPPLIER;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.DeleteSupplierCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteSupplierCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteSupplierCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteSupplierCommandParserTest {

    private DeleteSupplierCommandParser parser = new DeleteSupplierCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteSupplierCommand(INDEX_FIRST_SUPPLIER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSupplierCommand.MESSAGE_USAGE));
    }
}
