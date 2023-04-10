package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ChangeCostCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ChangeCostCommand object
 */
public class ChangeCostCommandParser implements Parser<ChangeCostCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeCostCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.split(" ", 4);
            if (splitArgs.length != 4) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeCostCommand.MESSAGE_USAGE));
            }
            Index index = ParserUtil.parseIndex(splitArgs[1]);
            double rate = ParserUtil.parseDouble(splitArgs[2]);
            double flatCost = ParserUtil.parseDouble(splitArgs[3]);
            return new ChangeCostCommand(index, rate, flatCost);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeCostCommand.MESSAGE_USAGE), pe);
        }
    }

}
