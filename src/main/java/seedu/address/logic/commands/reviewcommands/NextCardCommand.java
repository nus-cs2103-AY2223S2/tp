package seedu.address.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;

/**
 * Sets the next card as the card currently under review.
 */
public class NextCardCommand extends Command {

    public static final String COMMAND_WORD = "]";

    public static final String MESSAGE_SUCCESS = "Skipped to next flashcard.";
    public static final String MESSAGE_NO_MORE_NEXT_CARD = "This is the last card.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.goToNextCard()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_MORE_NEXT_CARD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextCardCommand); // instanceof handles nulls
    }
}
