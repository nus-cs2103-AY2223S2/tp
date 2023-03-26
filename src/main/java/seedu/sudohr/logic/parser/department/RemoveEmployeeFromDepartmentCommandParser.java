package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import seedu.sudohr.logic.commands.department.RemoveEmployeeFromDepartmentCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Id;

/**
 * Parses input arguments and creates a new RemoveEmployeeFromDepartmentCommand object
 */
public class RemoveEmployeeFromDepartmentCommandParser implements Parser<RemoveEmployeeFromDepartmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveEmployeeFromDepartmentCommand
     * and returns an RemoveEmployeeFromDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveEmployeeFromDepartmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMPLOYEE, PREFIX_DEPARTMENT_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EMPLOYEE, PREFIX_DEPARTMENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveEmployeeFromDepartmentCommand.MESSAGE_USAGE));
        }

        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_EMPLOYEE).get());
        DepartmentName name = ParserUtil.parseDepartmentName(argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get());

        return new RemoveEmployeeFromDepartmentCommand(id, name);
    }
}
