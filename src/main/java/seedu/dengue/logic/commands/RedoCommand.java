package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

/**
 * A command that reverses an undo operation.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.redo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
