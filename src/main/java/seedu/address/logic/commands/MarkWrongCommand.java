package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Marks the current card under review as wrong.
 */
public class MarkWrongCommand extends Command {

    public static final String COMMAND_WORD = ";";

    public static final String MESSAGE_SUCCESS = "You'll get it next time! Showing next flashcard."
            + "\nEnter [ to flip card and show answer!"
            + "\nEnter ] to return to previous card."
            + "\nEnter \\ to skip to next card.";

    public static final String MESSAGE_NO_MORE_NEXT_CARD = "Congrats! That was the last card.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.markWrong()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_MORE_NEXT_CARD);
        }
    }
}
