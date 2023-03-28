package seedu.loyaltylift.logic.parser;

import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.RevertOrderStatusCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RevertOrderStatusCommand object
 */
public class RevertOrderStatusCommandParser implements Parser<RevertOrderStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RevertOrderStatusCommand
     * and returns a RevertOrderStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RevertOrderStatusCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RevertOrderStatusCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RevertOrderStatusCommand.MESSAGE_USAGE), pe);
        }
    }
}
