package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DATE_DESC_LEAVE_TYPE_1;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_EID_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_LEAVE_DATE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_1;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.leave.DeleteEmployeeFromLeaveCommand;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.LeaveDate;

public class DeleteEmployeeFromLeaveCommandParserTest {
    private DeleteEmployeeFromLeaveCommandParser parser = new DeleteEmployeeFromLeaveCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EID_DESC_AMY + DATE_DESC_LEAVE_TYPE_1,
                new DeleteEmployeeFromLeaveCommand(new Id(VALID_ID_AMY),
                        new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))));
        assertParseSuccess(parser, EID_DESC_AMY + DATE_DESC_LEAVE_TYPE_1,
                new DeleteEmployeeFromLeaveCommand(new Id(VALID_ID_AMY),
                        new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEmployeeFromLeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, EID_DESC_AMY, expectedMessage);
        assertParseFailure(parser, DATE_DESC_LEAVE_TYPE_1, expectedMessage);
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEmployeeFromLeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_EID_DESC + DATE_DESC_LEAVE_TYPE_1,
                Id.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, EID_DESC_AMY + INVALID_LEAVE_DATE_DESC,
                MESSAGE_INVALID_DATE_FORMAT);
    }
}
