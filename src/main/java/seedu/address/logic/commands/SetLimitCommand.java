package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sets the limit for the number of cards per review session.
 */
public class SetLimitCommand extends Command {
    public static final String COMMAND_WORD = "setLimit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Set the limit for the "
            + "number of cards tested per review session.\n"
            + "Parameter: Integer (must be a positive integer) or 'none'.\n"
            + "Example: " + COMMAND_WORD + " 30";

    public static final String MESSAGE_SUCCESS_SET_LIMIT = "You have set the review limit to %1$s cards!";
    public static final String MESSAGE_SUCCESS_NO_LIMIT = "You have set the review limit to none - each review will "
            + "go through all cards once!";
    private final int reviewLimit;

    /**
     * Creates a SetLimitCommand with the specified index of the deck.
     */
    public SetLimitCommand(int reviewLimit) {
        requireNonNull(reviewLimit);
        this.reviewLimit = reviewLimit;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setReviewLimit(reviewLimit);
        if (reviewLimit < 0) {
            return new CommandResult(MESSAGE_SUCCESS_NO_LIMIT);
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS_SET_LIMIT, reviewLimit));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetLimitCommand // instanceof handles nulls
                && reviewLimit == (((SetLimitCommand) other).reviewLimit));
    }

}


