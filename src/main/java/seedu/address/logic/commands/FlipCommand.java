package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.review.Review;

/**
 * Flips the current flashcard under review to show its answer to the user.
 */
public class FlipCommand extends Command {

    public static final String COMMAND_WORD = "f";

    public static final String MESSAGE_SUCCESS = "Flashcard is flipped! Showing answer.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getReview().ifPresent(Review::flipCard);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
