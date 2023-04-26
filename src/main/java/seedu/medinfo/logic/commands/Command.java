package seedu.medinfo.logic.commands;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;

/**
 * Represents a {@code Command} with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the {@code Command} and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
