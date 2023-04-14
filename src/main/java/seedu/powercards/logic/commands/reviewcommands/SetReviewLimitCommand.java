package seedu.powercards.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;

/**
 * Sets the number of cards tested per review session.
 */
public class SetReviewLimitCommand extends Command {
    public static final String COMMAND_WORD = "setLimit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Limit the number of cards tested per review session.\n"
            + "Parameter: A positive integer between 1 and 2147483647 inclusive or the String 'none'.\n"
            + "Example: " + COMMAND_WORD + " 30";

    public static final String MESSAGE_SUCCESS_SET_LIMIT = "You have limited the number of cards per review to %1$s!";
    public static final String MESSAGE_SUCCESS_NO_LIMIT = "You have set the limit of cards per review to 'none' "
            + "- so each review will go through all cards in the deck once!";
    private final int numCardsPerReview;

    /**
     * Creates a SetReviewLimitCommand with the given number of cards.
     */
    public SetReviewLimitCommand(int numCardsPerReview) {
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
                || (other instanceof SetReviewLimitCommand // instanceof handles nulls
                && numCardsPerReview == (((SetReviewLimitCommand) other).numCardsPerReview));
    }

}


