package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReviewCommand;
import seedu.address.logic.commands.SetLimitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetLimitCommand object
 */
public class SetLimitCommandParser implements Parser<SetLimitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetLimitCommand
     * and returns an SetLimitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetLimitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        int reviewLimit;
        try {
            reviewLimit = ParserUtil.parseLimit(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetLimitCommand.MESSAGE_USAGE), pe);
        }

        return new SetLimitCommand(reviewLimit);
    }
}
