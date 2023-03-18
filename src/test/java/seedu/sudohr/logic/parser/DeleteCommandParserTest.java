package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_EID_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_EID_DESC_ZERO;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;


import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.DeleteCommand;
import seedu.sudohr.model.employee.Id;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        Id expectedId = new Id(VALID_ID_BOB);
        assertParseSuccess(parser,  EID_DESC_BOB, new DeleteCommand(expectedId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
        assertParseFailure(parser, VALID_ID_BOB, expectedMessage);
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY, expectedMessage);
    }

    @Test
    public void parse_additionalFieldsProvided_failure() {
        assertParseFailure(parser,  EID_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY, Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, INVALID_EID_DESC_ZERO, expectedMessage);
        assertParseFailure(parser, INVALID_EID_DESC, expectedMessage);

    }
}
