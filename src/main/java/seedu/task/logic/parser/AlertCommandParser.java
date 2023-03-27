package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.task.logic.commands.AlertCommand;
import seedu.task.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AlertCommand object
 */
public class AlertCommandParser implements Parser<AlertCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AlertCommand
     * and returns a AlertCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AlertCommand parse(String args) throws ParseException {
        try {
            if (args.isBlank()) {
                return new AlertCommand();
            }
            // assume that args might have multiple integers, of which we should take the last one
            String[] timeframes = args.split(" ");
            if (!onlyDigits(timeframes[timeframes.length - 1])) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AlertCommand.MESSAGE_USAGE));
            }
            int timeframe = ParserUtil.parseTimeFrame(timeframes[timeframes.length - 1].trim());
            return new AlertCommand(timeframe);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AlertCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if the input is only whitespaces and digits, else false.
     */
    public boolean onlyDigits(String input) {
        String[] inputs = input.split(" ");
        for (String i: inputs) {
            if (i.trim().chars().allMatch(Character::isDigit)) {
                continue;
            }
            return false;
        }
        return true;
    }
}
