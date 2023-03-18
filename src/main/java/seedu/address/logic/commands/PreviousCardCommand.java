package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sets the previous card as the card currently under review.
 */
public class PreviousCardCommand extends Command {

    public static final String COMMAND_WORD = "]";

    public static final String MESSAGE_SUCCESS = "Went back to previous flashcard."
            + "\nEnter [ to flip card and show answer!"
            + "\nEnter ] to return to previous card."
            + "\nEnter \\ to skip to next card.";
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
}
