package seedu.vms.logic.commands;

import java.util.Optional;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;

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
    public abstract CommandMessage execute(Model model) throws CommandException;


    /**
     * Returns the follow up command to be executed after this if its execution
     * was successful. Returned result is wrapped in an {@code Optional}. If
     * there are no follow up commands, {@code Optional.empty} is returned
     * instead.
     *
     * @return the follow up command to be executed after this, wrapped in an
     *      {@code Optional}.
     */
    public Optional<Command> getFollowUp() {
        return Optional.empty();
    }

}
