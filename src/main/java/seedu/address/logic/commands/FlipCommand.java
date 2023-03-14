package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Flips the current flashcard under review to show its answer to the user.
 */
public class FlipCommand extends Command {

    public static final String COMMAND_WORD = "f";

    public static final String MESSAGE_SUCCESS = "Flashcard is flipped! Showing answer.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.flipCard(); // Todo: any possible exception here?
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
