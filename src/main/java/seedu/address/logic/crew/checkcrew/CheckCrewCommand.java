package seedu.address.logic.crew.checkcrew;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exception.IndexOutOfBoundException;


/**
 * The command that checks a crew's availability in the Wingman app.
 */
public class CheckCrewCommand implements Command {
    /**
     * The UUID of the crew whose availability is to be checked.
     */
    private final String id;

    private static final String CREW_NOT_FOUND_EXCEPTION =
            "Crew with ID %s can't be found.";

    /**
     * Creates a command that, when executed, checks the availability of the crew with the given UUID.
     *
     * @param id the UUID of the crew to be checked.
     */
    public CheckCrewCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isAvailable;
        try {
            isAvailable = model.checkCrewByIndex(Integer.parseInt(this.id));
        } catch (IndexOutOfBoundException e) {
            // TODO: this error doesn't show up in the UI
            return new CommandResult(CREW_NOT_FOUND_EXCEPTION);
        }

        if (isAvailable) {
            return new CommandResult("This crew is available.");
        } else {
            return new CommandResult("This crew is unavailable.");
        }
    }
}
