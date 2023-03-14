package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undo the previous command by tracing back to previous AddressBook
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_SUCCESS = "Undo success";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndo()) {
            throw new CommandException(Messages.MESSAGE_CANNOT_UNDO);
        }
        model.undo();
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }

}
