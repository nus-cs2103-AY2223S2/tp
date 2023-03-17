package seedu.sudohr.logic.parser.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;

import java.util.stream.Stream;

import seedu.sudohr.logic.commands.department.RemoveEmployeeFromDepartmentCommand;
import seedu.sudohr.logic.parser.*;
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
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_DEPARTMENT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_DEPARTMENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveEmployeeFromDepartmentCommand.MESSAGE_USAGE));
        }

        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        DepartmentName name = ParserUtil.parseDepartmentName(argMultimap.getValue(PREFIX_DEPARTMENT_NAME).get());

        return new RemoveEmployeeFromDepartmentCommand(id, name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
