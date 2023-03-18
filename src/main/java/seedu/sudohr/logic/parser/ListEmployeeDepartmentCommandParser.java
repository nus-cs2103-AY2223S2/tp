package seedu.sudohr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;

import seedu.sudohr.logic.commands.ListEmployeeDepartmentCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.department.DepartmentContainsEmployeePredicate;
import seedu.sudohr.model.employee.Id;

/**
 * Parses input arguments and creates a new ListEmployeeDepartmentCommand object
 */
public class ListEmployeeDepartmentCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ListEmployeeDepartmentCommand
     * and returns a ListEmployeeDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListEmployeeDepartmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID);

        Id id;

        try {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListEmployeeDepartmentCommand.MESSAGE_USAGE), pe);
        }

        return new ListEmployeeDepartmentCommand(new DepartmentContainsEmployeePredicate(id));
    }

}
