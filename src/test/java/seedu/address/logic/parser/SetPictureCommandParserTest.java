package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetPictureCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the SetPictureCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the SetPictureCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SetPictureCommandParserTest {

    private SetPictureCommandParser parser = new SetPictureCommandParser();

    @Test
    public void parse_validArgs_returnsSetPictureCommand() {
        assertParseSuccess(parser, "1", new SetPictureCommand(EMPLOYEE_ID_ONE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPictureCommand.MESSAGE_USAGE));
    }
}
