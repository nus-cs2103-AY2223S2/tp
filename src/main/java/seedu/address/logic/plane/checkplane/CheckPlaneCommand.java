package seedu.address.logic.plane.checkplane;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exception.IndexOutOfBoundException;


/**
 * The command that checks a plane's availability in the Wingman app.
 */
public class CheckPlaneCommand implements Command {
    /**
     * The UUID of the plane whose availability is to be checked.
     */
    private final String id;

    /**
     * Creates a command that, when executed, checks the availability of the plane with the given UUID.
     *
     * @param id the UUID of the plane to be checked.
     */
    public CheckPlaneCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int index = Command.parseIntegerToZeroBasedIndex(id);
        boolean isAvailable;
        try {
            isAvailable = model.checkPlaneByIndex(index);
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        if (isAvailable) {
            return new CommandResult("This plane is available.");
        } else {
            return new CommandResult("This plane is unavailable.");
        }
    }
}
