package seedu.address.logic.plane.checkplane;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * The command that checks a plane's availability in the Wingman app.
 */
public class CheckPlaneCommand implements Command {
    /**
     * The UUID of the plane whose availability is to be checked.
     */
    private final String uuid;

    /**
     * Creates a command that, when executed, checks the availability of the plane with the given UUID.
     *
     * @param uuid the UUID of the plane to be checked.
     */
    public CheckPlaneCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isAvailable = model.checkPlane(this.uuid);
        if (isAvailable) {
            return new CommandResult("This plane is available.");
        } else {
            return new CommandResult("This plane is unavailable.");
        }
    }
}
