package seedu.powercards.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;

/**
 * Ends current review session.
 */
public class EndReviewCommand extends Command {

    public static final String COMMAND_WORD = "endReview";

    public static final String MESSAGE_SUCCESS = "Ended the review.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.endReview();
        return new CommandResult(MESSAGE_SUCCESS,
                false, false, false, true, false, false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndReviewCommand); // instanceof handles nulls
    }
}
