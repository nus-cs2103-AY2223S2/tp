package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.List;

import seedu.sudohr.logic.commands.leave.AddEmployeeToLeaveFromToCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * Parses input arguments and creates a new AddEmployeeToLeaveFromToCommandParser
 * object
 */
public class AddEmployeeToLeaveFromToCommandParser implements Parser<AddEmployeeToLeaveFromToCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddEmployeeToLeaveFromToCommandParser
     * and returns an AddEmployeeToLeaveCommandFromTo object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEmployeeToLeaveFromToCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EMPLOYEE, PREFIX_START_DATE,
                PREFIX_END_DATE);

        if (!ParserUtil.arePrefixesPresent(argMultimap,
                PREFIX_START_DATE,
                PREFIX_END_DATE,
                PREFIX_EMPLOYEE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEmployeeToLeaveFromToCommand.MESSAGE_USAGE));
        }

        List<LeaveDate> leaveDates = ParserUtil.parseLeaveDateFromTo(argMultimap.getValue(PREFIX_START_DATE).get(),
                argMultimap.getValue(PREFIX_END_DATE).get());
        Id employeeId = ParserUtil.parseId(argMultimap.getValue(PREFIX_EMPLOYEE).get());
        return new AddEmployeeToLeaveFromToCommand(employeeId, leaveDates);
    }
}
