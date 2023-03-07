package arb.logic.commands;

import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param currentListBeingShown {@code ListType} that is currently being shown to the user.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException;

}
