package seedu.address.logic.parser.documents;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.documents.DeleteDocumentsCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteDocumentsCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteDocumentsCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteDocumentsCommandParserTest {

    private DeleteDocumentsCommandParser parser = new DeleteDocumentsCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteDocumentCommand() {
        assertParseSuccess(parser, "1", new DeleteDocumentsCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteDocumentsCommand.MESSAGE_USAGE));
    }
}
