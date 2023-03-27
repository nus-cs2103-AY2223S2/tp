package seedu.address.logic.toplevel.changemode;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OperationMode;

/**
 * The command that changes the operation mode of the application.
 */
public class ChangeModeCommand implements Command {
    /**
     * The mode to change to.
     */
    private final OperationMode mode;

    public ChangeModeCommand(OperationMode mode) {
        this.mode = mode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setOperationMode(mode);
        return new CommandResult("Currently in " + mode + " mode.");
    }
}
