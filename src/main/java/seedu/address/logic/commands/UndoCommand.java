package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Undo the last modification done to the address book.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undo the last modification done to the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Undo success!";
    public static final String MESSAGE_NOTHING_TO_UNDO = "There is nothing to undo";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.canUndo()) {
            model.undo();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NOTHING_TO_UNDO);
        }
    }
}
