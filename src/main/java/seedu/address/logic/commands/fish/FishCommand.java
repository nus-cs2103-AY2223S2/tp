package seedu.address.logic.commands.fish;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds Fish to Fish Ahoy!
 */
public abstract class FishCommand extends Command {
    public static final String COMMAND_WORD = "fish";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "<fish command word>\n"
            + "Fish commands: add, "
            + "delete, "
            + "view";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;
}
