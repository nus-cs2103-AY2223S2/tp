package seedu.powercards.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;

/**
 * Sets the previous card as the card currently under review.
 */
public class PreviousCardCommand extends Command {

    public static final String COMMAND_WORD = "[";

    public static final String MESSAGE_SUCCESS = "Went back to previous flashcard.";
    public static final String MESSAGE_NO_MORE_PREV_CARD = "This is the first card.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.goToPrevCard()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_MORE_PREV_CARD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PreviousCardCommand); // instanceof handles nulls
    }
}
