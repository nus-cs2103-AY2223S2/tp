package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.FilterByLeaveCountPredicate;
import seedu.address.model.employee.FilterByPayrollPredicate;
import seedu.address.model.employee.LeaveCounter;
import seedu.address.model.employee.Payroll;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @param args the arguments in string form.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] parameters = trimmedArgs.split("\\s+");
        int length = parameters.length;

        if (length != 3) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        String sortParameter = ParserUtil.parseSortParameter(parameters);
        boolean[] possibleOperators = ParserUtil.parseComparisonSign(parameters);
        int comparisonAmount = ParserUtil.parseComparisonAmount(parameters);

        switch(sortParameter) {

        case Payroll.FILTER_PARAMETER:
            return new FilterCommand(new FilterByPayrollPredicate(
                    comparisonAmount, possibleOperators));
        case LeaveCounter.FILTER_PARAMETER:
            return new FilterCommand(new FilterByLeaveCountPredicate(
                    comparisonAmount, possibleOperators));
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        }
    }
}
