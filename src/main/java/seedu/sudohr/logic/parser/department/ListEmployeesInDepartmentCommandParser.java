package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.department.ListEmployeesInDepartmentCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;


/**
 * Parses input arguments and creates a new ListEmployeesInDepartmentCommand object.
 */
public class ListEmployeesInDepartmentCommandParser implements Parser<ListEmployeesInDepartmentCommand> {
    @Override
    public ListEmployeesInDepartmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DEPARTMENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListEmployeesInDepartmentCommand.MESSAGE_USAGE));
        }

        DepartmentName departmentName = ParserUtil.parseDepartmentName(
                argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get());

        return new ListEmployeesInDepartmentCommand(departmentName);
    }
}
