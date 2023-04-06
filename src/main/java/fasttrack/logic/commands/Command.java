package fasttrack.logic.commands;

import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public interface Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param dataModel {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model dataModel) throws CommandException;

}
