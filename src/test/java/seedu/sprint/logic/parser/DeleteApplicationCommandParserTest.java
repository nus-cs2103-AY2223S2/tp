package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseSuccess;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import org.junit.jupiter.api.Test;

import seedu.sprint.logic.commands.DeleteApplicationCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteApplicationCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteApplicationCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteApplicationCommandParserTest {

    private DeleteApplicationCommandParser parser = new DeleteApplicationCommandParser();

    @Test
    public void parse_validOneArg_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteApplicationCommand(INDEX_FIRST_APPLICATION));
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicationCommand.MESSAGE_USAGE));
    }
}
