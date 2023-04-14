package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DATE_DESC_LEAVE_TYPE_1;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_LEAVE_DATE_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.DATE_TYPE_1;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.department.ListDepartmentHeadcountCommand;
import seedu.sudohr.model.department.DepartmentName;


public class ListDepartmentHeadcountCommandParserTest {

    private ListDepartmentHeadcountCommandParser parser = new ListDepartmentHeadcountCommandParser();


    @Test
    public void parse_validArgs_parseSuccess() {
        // cannot test automatically due to LocalDate.now()

    }


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDepartmentHeadcountCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDateTimeFormat_throwsParseException() {
        String userInput = DEPARTMENT_NAME_DESC_ENGINEERING + INVALID_LEAVE_DATE_DESC;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_DATE_FORMAT);
    }

    @Test
    public void parse_invalidDepartmentName_throwsParseException() {
        String userInput = DEPARTMENT_NAME_DESC_ENGINEERING + "! " + PREFIX_DATE + DATE_TYPE_1;
        assertParseFailure(parser, userInput, DepartmentName.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        String userInput = PREAMBLE_NON_EMPTY + " " + DEPARTMENT_NAME_DESC_ENGINEERING
                + DATE_DESC_LEAVE_TYPE_1;

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDepartmentHeadcountCommand.MESSAGE_USAGE));
    }



}
