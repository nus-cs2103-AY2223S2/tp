package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;
import static seedu.sudohr.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.sudohr.logic.commands.leave.ListLeavesByEmployeeCommand;
import seedu.sudohr.logic.parser.ArgumentMultimap;
import seedu.sudohr.logic.parser.ArgumentTokenizer;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.employee.Id;


/**
 * Parses arguments and creates a new ListLeavesByEmployeeCommand object.
 */
public class ListLeavesByEmployeeCommandParser implements Parser<ListLeavesByEmployeeCommand> {
    @Override
    public ListLeavesByEmployeeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EMPLOYEE);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_EMPLOYEE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListLeavesByEmployeeCommand.MESSAGE_USAGE));
        }

        Id employeeId = ParserUtil.parseId(argMultimap.getValue(PREFIX_EMPLOYEE).get());

        return new ListLeavesByEmployeeCommand(employeeId);
    }
}
