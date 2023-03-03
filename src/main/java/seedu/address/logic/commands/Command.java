package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public interface Command {
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(Model model)
            throws CommandException;

    /**
     * Reverses the "harm" that this command has done to the model.
     * <p>
     * TODO: enable this in future iterations by removing the default
     * implementation. We can implement undo/redo by storing the Commands as
     * a stack and then popping them off to reverse the effects.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error occurs during command execution.
     */
    default void reverse(Model model) throws CommandException {
        throw new CommandException("This command cannot be reversed.");
    }
}
