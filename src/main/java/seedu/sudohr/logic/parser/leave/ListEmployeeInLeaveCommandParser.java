package seedu.sudohr.logic.parser.leave;

import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.sudohr.logic.commands.leave.ListEmployeeInLeaveCommand;
import seedu.sudohr.logic.parser.Parser;
import seedu.sudohr.logic.parser.ParserUtil;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.leave.LeaveDate;

/**
 * Parses input arguments and creates a new ListEmployeeInLeaveCommandParser
 * object
 */
public class ListEmployeeInLeaveCommandParser implements Parser<ListEmployeeInLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ListEmployeeInLeaveCommandParser
     * and returns a ListEmployeeInLeaveCommandParser object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListEmployeeInLeaveCommand parse(String args) throws ParseException {
        try {
            LeaveDate date = ParserUtil.parseLeaveDate(args);
            return new ListEmployeeInLeaveCommand(date);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEmployeeInLeaveCommand.MESSAGE_USAGE), pe);
        }
    }
}
