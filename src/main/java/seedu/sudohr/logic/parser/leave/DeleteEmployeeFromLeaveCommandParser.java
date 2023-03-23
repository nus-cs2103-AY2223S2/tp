package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;

import java.util.stream.Stream;

import seedu.sudohr.logic.commands.leave.DeleteEmployeeFromLeaveCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.Prefix;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * Parses input arguments and creates a new DeleteEmployeeFromLeaveCommandParser
 * object
 */
public class DeleteEmployeeFromLeaveCommandParser implements Parser<DeleteEmployeeFromLeaveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteEmployeeFromLeaveCommand
     * and returns a DeleteEmployeeFromLeaveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteEmployeeFromLeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_EMPLOYEE);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_DATE,
                PREFIX_EMPLOYEE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteEmployeeFromLeaveCommand.MESSAGE_USAGE));
        }

        LeaveDate leaveDate = ParserUtil.parseLeaveDate(argMultimap.getValue(PREFIX_DATE).get());
        Id employeeId = ParserUtil.parseId(argMultimap.getValue(PREFIX_EMPLOYEE).get());

        return new DeleteEmployeeFromLeaveCommand(employeeId, leaveDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
