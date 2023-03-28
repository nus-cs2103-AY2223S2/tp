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
            isAvailable = model.checkCrewByIndex(
                    Command.parseIntegerToZeroBasedIndex(id)
            );
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        if (isAvailable) {
            return new CommandResult("This crew is available.");
        } else {
            return new CommandResult("This crew is unavailable.");
        }
    }
}
