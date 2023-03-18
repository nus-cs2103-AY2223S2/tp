package seedu.sudohr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;

import seedu.sudohr.logic.commands.AddEmployeeToDepartmentCommand;
import seedu.sudohr.logic.commands.ListEmployeeDepartmentCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentContainsEmployeePredicate;
import seedu.sudohr.model.employee.Id;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new ListEmployeeDepartmentCommand object
 */
public class ListEmployeeDepartmentCommandParser implements Parser<ListEmployeeDepartmentCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the ListEmployeeDepartmentCommand
     * and returns a ListEmployeeDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListEmployeeDepartmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListEmployeeDepartmentCommand.MESSAGE_USAGE));
        }

        Id id;

        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListEmployeeDepartmentCommand.MESSAGE_USAGE), pe);
        }

        return new ListEmployeeDepartmentCommand(new DepartmentContainsEmployeePredicate(id));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
