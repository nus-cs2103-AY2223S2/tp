package seedu.address.logic.pilot.checkpilot;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * The command that checks a pilot's availability in the Wingman app.
 */
public class CheckPilotCommand implements Command {
    /**
     * The UUID of the pilot whose availability is to be checked.
     */
    private final String uuid;

    /**
     * Creates a command that, when executed, checks the availability of the pilot with the given UUID.
     *
     * @param uuid the UUID of the pilot to be checked.
     */
    public CheckPilotCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isAvailable = model.checkPilot(this.uuid);
        if (isAvailable) {
            return new CommandResult("This pilot is available.");
        } else {
            return new CommandResult("This pilot is unavailable.");
        }
    }
}
