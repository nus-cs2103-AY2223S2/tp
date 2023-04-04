package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static ezschedule.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static ezschedule.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private final DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        List<Index> indexFirstEvent = new ArrayList<>();
        indexFirstEvent.add(INDEX_FIRST_EVENT);
        assertParseSuccess(parser, "1", new DeleteCommand(indexFirstEvent));
    }

    @Test
    public void parse_multipleValidArgs_returnsDeleteCommand() {
        List<Index> multipleIndexes = new ArrayList<>();
        multipleIndexes.add(INDEX_FIRST_EVENT);
        multipleIndexes.add(INDEX_SECOND_EVENT);
        multipleIndexes.add(INDEX_THIRD_EVENT);
        assertParseSuccess(parser, "1 2 3", new DeleteCommand(multipleIndexes));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_mixOfValidAndInvalidIndexes_throwsParseException() {
        assertParseFailure(parser, "-2 0 1 4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
