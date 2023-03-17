package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sets the next card as the card currently under review.
 */
public class NextCardCommand extends Command {

    public static final String COMMAND_WORD = "\\";

    public static final String MESSAGE_SUCCESS = "Skipped to next flashcard."
            + "\nEnter [ to flip card and show answer!"
            + "\nEnter ] to return to previous card."
            + "\nEnter \\ to skip to next card.";
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
}
