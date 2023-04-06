package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DATE_DESC_END_LEAVE_DATE;
import static seedu.sudohr.logic.commands.CommandTestUtil.DATE_DESC_START_LEAVE_DATE;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_DESC_END_LEAVE_DATE_BEFORE_START;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_DESC_END_LEAVE_DATE_MORE_THAN_SEVEN;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_EID_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_END_LEAVE_DATE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_SECOND_DAY_LEAVE_DATE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_START_LEAVE_DATE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_THIRD_DAY_LEAVE_DATE;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.leave.AddEmployeeToLeaveFromToCommand;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.LeaveDate;

public class AddEmployeeToLeaveCommandFromToParserTest {
    private AddEmployeeToLeaveFromToCommandParser parser = new AddEmployeeToLeaveFromToCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        List<LeaveDate> leaveToInsert = new ArrayList<LeaveDate>();
        leaveToInsert.add(new LeaveDate(LocalDate.parse(
                VALID_START_LEAVE_DATE)));

        leaveToInsert.add(new LeaveDate(LocalDate.parse(
                VALID_SECOND_DAY_LEAVE_DATE)));

        leaveToInsert.add(new LeaveDate(LocalDate.parse(
                VALID_THIRD_DAY_LEAVE_DATE)));
        leaveToInsert.add(new LeaveDate(LocalDate.parse(
                VALID_END_LEAVE_DATE)));

        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + EID_DESC_AMY + DATE_DESC_START_LEAVE_DATE + DATE_DESC_END_LEAVE_DATE,
                new AddEmployeeToLeaveFromToCommand(new Id(VALID_ID_AMY), leaveToInsert));
        assertParseSuccess(parser, EID_DESC_AMY + DATE_DESC_START_LEAVE_DATE + DATE_DESC_END_LEAVE_DATE,
                new AddEmployeeToLeaveFromToCommand(new Id(VALID_ID_AMY), leaveToInsert));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEmployeeToLeaveFromToCommand.MESSAGE_USAGE);

        assertParseFailure(parser, EID_DESC_AMY, expectedMessage);
        assertParseFailure(parser, DATE_DESC_START_LEAVE_DATE, expectedMessage);
        assertParseFailure(parser, DATE_DESC_END_LEAVE_DATE, expectedMessage);
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEmployeeToLeaveFromToCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_EID_DESC + DATE_DESC_START_LEAVE_DATE + DATE_DESC_END_LEAVE_DATE,
                Id.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, EID_DESC_AMY + INVALID_START_DATE_DESC + DATE_DESC_END_LEAVE_DATE,
                Messages.MESSAGE_INVALID_DATE_FORMAT);
        assertParseFailure(parser, EID_DESC_AMY + DATE_DESC_START_LEAVE_DATE + INVALID_END_DATE_DESC,
                Messages.MESSAGE_INVALID_DATE_FORMAT);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, EID_DESC_AMY + DATE_DESC_START_LEAVE_DATE + INVALID_DESC_END_LEAVE_DATE_BEFORE_START,
                AddEmployeeToLeaveFromToCommand.DATE_CONSTRAINTS);
        assertParseFailure(parser, EID_DESC_AMY + DATE_DESC_START_LEAVE_DATE
                + INVALID_DESC_END_LEAVE_DATE_MORE_THAN_SEVEN, AddEmployeeToLeaveFromToCommand.DATE_CONSTRAINTS);
    }
}
