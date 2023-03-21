package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_LEAVE_DATE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_1;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.leave.ListEmployeeInLeaveCommand;
import seedu.sudohr.model.leave.LeaveDate;

public class ListEmployeeInLeaveCommandParserTest {

    private ListEmployeeInLeaveCommandParser parser = new ListEmployeeInLeaveCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, " " + VALID_LEAVE_DATE_LEAVE_TYPE_1,
                new ListEmployeeInLeaveCommand(new LeaveDate(LocalDate.parse(
                        VALID_LEAVE_DATE_LEAVE_TYPE_1))));
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListEmployeeInLeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListEmployeeInLeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, INVALID_LEAVE_DATE_DESC, expectedMessage);
    }
}
