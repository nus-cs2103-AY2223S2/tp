package seedu.address.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Flips the current flashcard under review to show its answer to the user.
 */
public class FlipCardCommand extends Command {

    public static final String COMMAND_WORD = "[";

    public static final String MESSAGE_FLIP_SUCCESS = "Flashcard is flipped! Showing answer."
            + "\nTo mark correct enter '"
            + "\nTo mark wrong enter ;";

    public static final String MESSAGE_UNFLIP_SUCCESS = "Flashcard is unflipped! Showing only the question."
            + "\nTo mark correct enter '"
            + "\nTo mark wrong enter ;";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.flipCard();
        return model.isReviewCardFlipped() ? new CommandResult(MESSAGE_FLIP_SUCCESS) : new CommandResult(MESSAGE_UNFLIP_SUCCESS);
    }
}
