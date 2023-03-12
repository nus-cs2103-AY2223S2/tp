package seedu.careflow.logic.commands;

import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(CareFlowModel model) throws CommandException;
}
