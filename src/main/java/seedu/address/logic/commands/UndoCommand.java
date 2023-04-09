package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverses the effects of the last command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo a command that alters listing book.\n";

    public static final String MESSAGE_SUCCESS = "Undo success";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPreviousState()) {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO);
        }

        model.undo();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
