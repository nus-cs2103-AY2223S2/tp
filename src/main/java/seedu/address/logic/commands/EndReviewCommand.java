package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
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
