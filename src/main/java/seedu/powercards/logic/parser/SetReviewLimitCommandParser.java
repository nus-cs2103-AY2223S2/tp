package seedu.powercards.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.powercards.logic.commands.reviewcommands.SetReviewLimitCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetLimitCommand object
 */
public class SetReviewLimitCommandParser implements Parser<SetReviewLimitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetLimitCommand
     * and returns an SetLimitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetReviewLimitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        int reviewLimit;
        try {
            reviewLimit = ParserUtil.parseReviewLimit(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetReviewLimitCommand.MESSAGE_USAGE), pe);
        }

        return new SetReviewLimitCommand(reviewLimit);
    }
}
