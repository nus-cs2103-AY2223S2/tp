package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.internship.logic.commands.CommandTestUtil.ONE_FIELD_PREDICATE;
import static seedu.internship.logic.commands.CommandTestUtil.SIMPLE_PREDICATE;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.DeleteFieldCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside from the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteFieldCommandParserTest {

    private DeleteFieldCommandParser parser = new DeleteFieldCommandParser();

    @Test
    public void parse_oneFieldOneArgument_returnsDeleteCommand() {
        assertParseSuccess(parser, " n/Amazon", new DeleteFieldCommand(SIMPLE_PREDICATE));
    }

    @Test
    public void parse_oneFieldTwoArgument_returnsDeleteCommand() {
        assertParseSuccess(parser, " n/Amazon n/Goldman", new DeleteFieldCommand(ONE_FIELD_PREDICATE));
    }

    @Test
    public void parse_oneFieldTwoArgumentWithWhitespace_returnsDeleteCommand() {
        assertParseSuccess(parser, "  n/Amazon   n/Goldman  ", new DeleteFieldCommand(ONE_FIELD_PREDICATE));
    }

    @Test
    public void parse_emptyArgument_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_MISSING_ARGUMENTS,
                DeleteFieldCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexArgument_throwsParseException() {
        assertParseFailure(parser, "1 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFieldCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFieldCommand.MESSAGE_USAGE));
    }
}
