package seedu.sudohr.logic.parser;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE_INDEX;

import java.util.stream.Stream;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.leavecommands.AddEmployeeToLeaveCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.leave.Date;

/**
 * Parses input arguments and creates a new AddEmployeeToLeaveCommandParser
 * object
 */
public class AddEmployeeToLeaveCommandParser implements Parser<AddEmployeeToLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddEmployeeToLeaveCommandParser
     * and returns an AddEmployeeToLeaveCommandParser object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEmployeeToLeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_EMPLOYEE_INDEX);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_DATE,
                PREFIX_EMPLOYEE_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEmployeeToLeaveCommand.MESSAGE_USAGE));
        }

        Date leaveDate = ParserUtil.parseLeaveDate(argMultimap.getValue(PREFIX_DATE).get());
        Index employeeIndex = ParserUtil.parseEmployeeIndex(argMultimap.getValue(PREFIX_EMPLOYEE_INDEX).get());

        return new AddEmployeeToLeaveCommand(employeeIndex, leaveDate);
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
