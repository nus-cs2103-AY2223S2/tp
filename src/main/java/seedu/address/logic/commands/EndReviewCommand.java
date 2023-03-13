package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

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
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
