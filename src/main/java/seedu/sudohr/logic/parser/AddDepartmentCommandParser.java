package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;

import seedu.sudohr.logic.commands.AddDepartmentCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Parses input arguments and creates a new AddDepartmentCommand object
 */
public class AddDepartmentCommandParser implements Parser<AddDepartmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDepartmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DEPARTMENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDepartmentCommand.MESSAGE_USAGE));
        }

        DepartmentName name = ParserUtil.parseDepartmentName(argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get());

        Department department = new Department(name);

        return new AddDepartmentCommand(department);
    }
}
