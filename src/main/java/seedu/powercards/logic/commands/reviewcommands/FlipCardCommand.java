package seedu.powercards.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;

/**
 * Flips the current flashcard under review to show its answer to the user.
 */
public class FlipCardCommand extends Command {

    public static final String COMMAND_WORD = "p";

    public static final String MESSAGE_FLIP_SUCCESS = "Flashcard is flipped! Showing answer.";

    public static final String MESSAGE_UNFLIP_SUCCESS = "Flashcard is unflipped! Showing only the question.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.flipCard();
        return model.isReviewCardFlipped()
                ? new CommandResult(MESSAGE_FLIP_SUCCESS)
                : new CommandResult(MESSAGE_UNFLIP_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlipCardCommand); // instanceof handles nulls
    }
}
