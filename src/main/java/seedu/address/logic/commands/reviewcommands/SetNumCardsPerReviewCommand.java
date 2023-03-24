package seedu.address.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sets the number of cards tested per review session.
 */
public class SetNumCardsPerReviewCommand extends Command {
    public static final String COMMAND_WORD = "setNumCardsPerReview";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Set the number of cards tested per review session.\n"
            + "Parameter: Integer (must be a positive integer) or 'all'.\n"
            + "Example: " + COMMAND_WORD + " 30";

    public static final String MESSAGE_SUCCESS_SET_LIMIT = "You have set the number of cards per review to %1$s cards!";
    public static final String MESSAGE_SUCCESS_NO_LIMIT = "You have set the number of cards per review to 'all' "
            + "- so each review will go through all cards in the deck once!";
    private final int numCardsPerReview;

    /**
     * Creates a SetNumCardsPerReviewCommand with the given number of cards.
     */
    public SetNumCardsPerReviewCommand(int numCardsPerReview) {
        requireNonNull(numCardsPerReview);
        this.numCardsPerReview = numCardsPerReview;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setNumCardsPerReview(numCardsPerReview);
        if (numCardsPerReview < 0) {
            return new CommandResult(MESSAGE_SUCCESS_NO_LIMIT);
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS_SET_LIMIT, numCardsPerReview));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetNumCardsPerReviewCommand // instanceof handles nulls
                && numCardsPerReview == (((SetNumCardsPerReviewCommand) other).numCardsPerReview));
    }

}


