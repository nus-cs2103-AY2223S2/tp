package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.DEPARTMENT_NAME_DESC_HUMAN_RESOURCES;
import static seedu.sudohr.logic.commands.CommandTestUtil.INVALID_DEPARTMENT_NAME_DESC;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.sudohr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_HUMAN_RESOURCES;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.sudohr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.DeleteDepartmentCommand;
import seedu.sudohr.model.department.DepartmentName;


public class DeleteDepartmentCommandParserTest {
    private DeleteDepartmentCommandParser parser = new DeleteDepartmentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {

        DepartmentName expected = new DepartmentName(VALID_DEPARTMENT_NAME_HUMAN_RESOURCES);

        // whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEPARTMENT_NAME_DESC_HUMAN_RESOURCES,
                new DeleteDepartmentCommand(expected));

        // multiple names - last name accepted
        assertParseSuccess(parser, DEPARTMENT_NAME_DESC_ENGINEERING + DEPARTMENT_NAME_DESC_HUMAN_RESOURCES,
                new DeleteDepartmentCommand(expected));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no prefix
        assertParseFailure(parser, VALID_DEPARTMENT_NAME_HUMAN_RESOURCES,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDepartmentCommand.MESSAGE_USAGE));

        // non-whitespace preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DEPARTMENT_NAME_DESC_ENGINEERING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDepartmentCommand.MESSAGE_USAGE));

        // invalid department name format
        assertParseFailure(parser, INVALID_DEPARTMENT_NAME_DESC, DepartmentName.MESSAGE_CONSTRAINTS);

    }
}
