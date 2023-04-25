package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSONS_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private static final String INVALID_DELETE_FORMAT_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "", INVALID_DELETE_FORMAT_MESSAGE);
        assertParseFailure(parser, "   ", INVALID_DELETE_FORMAT_MESSAGE);
    }

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(List.of(INDEX_FIRST_PERSON)));
        assertParseSuccess(parser, "2", new DeleteCommand(List.of(INDEX_SECOND_PERSON)));
        assertParseSuccess(parser, "  1  ", new DeleteCommand(List.of(INDEX_FIRST_PERSON)));
        assertParseSuccess(parser, "  2  ", new DeleteCommand(List.of(INDEX_SECOND_PERSON)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "0", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "-1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void parse_multipleValidArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2", new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
        assertParseSuccess(parser, "2 1", new DeleteCommand(List.of(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON)));
        assertParseSuccess(parser, "  1   2  ", new DeleteCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
        assertParseSuccess(parser, "  2   1  ", new DeleteCommand(List.of(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON)));
    }

    @Test
    public void parse_multipleInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "a 1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "0 1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "-1 1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "1 a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "1 0", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "1 -1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "   a  1   ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "   0  1   ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "   -1  1   ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "  1   a  ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "  1  0  ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertParseFailure(parser, "  1  -1  ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void parse_duplicateIndexes_throwsParseException() {
        assertParseFailure(parser, "1 1", MESSAGE_DUPLICATE_PERSONS_INDEX);
        assertParseFailure(parser, "2 2 2", MESSAGE_DUPLICATE_PERSONS_INDEX);
        assertParseFailure(parser, "1 2 2", MESSAGE_DUPLICATE_PERSONS_INDEX);
        assertParseFailure(parser, "  1   1 ", MESSAGE_DUPLICATE_PERSONS_INDEX);
        assertParseFailure(parser, "  2   2   2  ", MESSAGE_DUPLICATE_PERSONS_INDEX);
        assertParseFailure(parser, "  1   2   2  ", MESSAGE_DUPLICATE_PERSONS_INDEX);
    }

}
