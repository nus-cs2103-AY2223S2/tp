package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DATE_DESC_LEAVE_TYPE_1;
import static seedu.sudohr.logic.commands.CommandTestUtil.EMPLOYEE_INDEX_DESC_1;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_INDEX_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_LEAVE_DATE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_1;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.leavecommands.DeleteEmployeeFromLeaveCommand;
import seedu.sudohr.model.leave.LeaveDate;

public class DeleteEmployeeFromLeaveCommandParserTest {
    private DeleteEmployeeFromLeaveCommandParser parser = new DeleteEmployeeFromLeaveCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EMPLOYEE_INDEX_DESC_1 + DATE_DESC_LEAVE_TYPE_1,
                new DeleteEmployeeFromLeaveCommand(Index.fromOneBased(1), new LeaveDate(LocalDate.parse(
                        VALID_LEAVE_DATE_LEAVE_TYPE_1))));
        assertParseSuccess(parser, EMPLOYEE_INDEX_DESC_1 + DATE_DESC_LEAVE_TYPE_1,
                new DeleteEmployeeFromLeaveCommand(Index.fromOneBased(1), new LeaveDate(LocalDate.parse(
                        VALID_LEAVE_DATE_LEAVE_TYPE_1))));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEmployeeFromLeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, EMPLOYEE_INDEX_DESC_1, expectedMessage);
        assertParseFailure(parser, DATE_DESC_LEAVE_TYPE_1, expectedMessage);
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEmployeeFromLeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_INDEX_DESC + DATE_DESC_LEAVE_TYPE_1,
                ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, EMPLOYEE_INDEX_DESC_1 + INVALID_LEAVE_DATE_DESC,
                LeaveDate.MESSAGE_CONSTRAINTS);
    }
}
