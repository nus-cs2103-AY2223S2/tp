package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command to redo an action
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_SUCCESS = "Redo success";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedo()) {
            throw new CommandException(Messages.MESSAGE_CANNOT_REDO);
        }
        model.redo();
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}
