package seedu.address.logic.crew.checkcrew;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * The command that checks a crew's availability in the Wingman app.
 */
public class CheckCrewCommand implements Command {
    /**
     * The UUID of the crew whose availability is to be checked.
     */
    private final String uuid;

    /**
     * Creates a command that, when executed, checks the availability of the crew with the given UUID.
     *
     * @param uuid the UUID of the crew to be checked.
     */
    public CheckCrewCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isAvailable = model.checkCrew(this.uuid);
        if (isAvailable) {
            return new CommandResult("This crew is available.");
        } else {
            return new CommandResult("This crew is unavailable.");
        }
    }
}
