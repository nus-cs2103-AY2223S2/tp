package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.internship.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.internship.logic.commands.CommandTestUtil.MULTIPLE_INDEX_LIST;
import static seedu.internship.logic.commands.CommandTestUtil.NON_EMPTY_INDEXLIST;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.DeleteIndexCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside from the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteIndexCommandParserTest {

    private DeleteIndexCommandParser parser = new DeleteIndexCommandParser();

    @Test
    public void parse_oneIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteIndexCommand(NON_EMPTY_INDEXLIST));
    }

    @Test
    public void parse_multipleIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2", new DeleteIndexCommand(MULTIPLE_INDEX_LIST));
    }

    @Test
    public void parse_multipleIndexWithWhitespace_returnsDeleteCommand() {
        assertParseSuccess(parser, "   1    2   ", new DeleteIndexCommand(MULTIPLE_INDEX_LIST));
    }

    @Test
    public void parse_emptyArgument_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_MISSING_ARGUMENTS,
                DeleteIndexCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_parameterArgument_throwsParseException() {
        assertParseFailure(parser, "n/google", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteIndexCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteIndexCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }
}
