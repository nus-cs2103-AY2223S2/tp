package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.leave.ListLeavesByEmployeeCommand;
import seedu.sudohr.model.employee.Id;


public class ListLeavesByEmployeeCommandParserTest {


    private ListLeavesByEmployeeCommandParser parser = new ListLeavesByEmployeeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, EID_DESC_AMY, new ListLeavesByEmployeeCommand(new Id(VALID_ID_AMY)));

        // multiple white space
        assertParseSuccess(parser, " \n " + EID_DESC_AMY + " \n ",
                new ListLeavesByEmployeeCommand(new Id(VALID_ID_AMY)));
    }

    @Test
    public void parser_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListLeavesByEmployeeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parser_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EID_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListLeavesByEmployeeCommand.MESSAGE_USAGE));
    }



}
