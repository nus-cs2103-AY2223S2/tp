package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.AdvanceOrderStatusCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AdvanceOrderStatusCommand object
 */
public class AdvanceOrderStatusCommandParser implements Parser<AdvanceOrderStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AdvanceOrderStatusCommand
     * and returns a AdvanceOrderStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AdvanceOrderStatusCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AdvanceOrderStatusCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AdvanceOrderStatusCommand.MESSAGE_USAGE), pe);
        }
    }
}
