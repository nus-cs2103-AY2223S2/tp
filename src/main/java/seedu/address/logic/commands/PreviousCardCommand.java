package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class PreviousCardCommand extends Command {

    public static final String COMMAND_WORD = "]";

    public static final String MESSAGE_SUCCESS = "Went back to previous flashcard.\nEnter [ to flip card and show answer!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.goToPrevCard();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
