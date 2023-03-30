package wingman.logic.toplevel.changemode;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.OperationMode;

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
