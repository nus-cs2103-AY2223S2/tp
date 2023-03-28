package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.patient.DetailCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DetailCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DetailCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DetailCommandParserTest {

    private DetailCommandParser parser = new DetailCommandParser();

    @Test
    public void parse_validArgs_returnsDetailCommand() {
        assertParseSuccess(parser, "1", new DetailCommand(INDEX_FIRST_PATIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailCommand.MESSAGE_USAGE));
    }
}
