package seedu.fitbook.logic.parser.routine;

import static seedu.fitbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_ROUTINE;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.DeleteRoutineCommand;
import seedu.fitbook.logic.parser.DeleteRoutineCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteRoutineCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteRoutineCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteRoutineCommandParserTest {

    private DeleteRoutineCommandParser parser = new DeleteRoutineCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteRoutineCommand(INDEX_FIRST_ROUTINE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteRoutineCommand.MESSAGE_USAGE));
    }
}
