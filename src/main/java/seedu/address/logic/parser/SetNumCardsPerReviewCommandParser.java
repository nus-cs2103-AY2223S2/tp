package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetNumCardsPerReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetLimitCommand object
 */
public class SetNumCardsPerReviewCommandParser implements Parser<SetNumCardsPerReviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetLimitCommand
     * and returns an SetLimitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetNumCardsPerReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        int reviewLimit;
        try {
            reviewLimit = ParserUtil.parseNumCardsPerReview(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetNumCardsPerReviewCommand.MESSAGE_USAGE), pe);
        }

        return new SetNumCardsPerReviewCommand(reviewLimit);
    }
}
