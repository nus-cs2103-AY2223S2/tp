package trackr.logic.commands;

import trackr.logic.commands.exceptions.CommandException;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException, ParseException;

}
