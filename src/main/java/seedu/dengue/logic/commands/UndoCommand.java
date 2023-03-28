package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

/**
 * A command that reverses a change previously made, or reverses a redo operation.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful!";

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
