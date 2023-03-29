package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE_COUNT;

import seedu.address.logic.commands.LeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.EmployeeId;

/**
 * Parses input arguments and creates a new LeaveCommand object
 */
public class LeaveCommandParser implements Parser<LeaveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LeaveCommand
     * and returns a LeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LEAVE_COUNT);
        EmployeeId employeeId;
        int leaveCount;

        try {
            employeeId = ParserUtil.parseEmployeeId(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE), pe);
        }
        if (argMultimap.getValue(PREFIX_LEAVE_COUNT).isPresent()) {
            leaveCount = ParserUtil.parseLeaveCount(argMultimap.getValue(PREFIX_LEAVE_COUNT)).getLeaveCount();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE));
        }
        return new LeaveCommand(employeeId, leaveCount);
    }
}
