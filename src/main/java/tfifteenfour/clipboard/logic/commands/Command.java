package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /** Whether the command will modify the rosterstate */
    protected boolean willModifyState;

    public Command(boolean willModifyState) {
        this.willModifyState = willModifyState;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model)
            throws CommandException;

    public boolean getWillModifyState() {
        return this.willModifyState;
    }

}
