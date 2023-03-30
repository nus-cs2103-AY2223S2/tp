package wingman.logic.pilot.checkpilot;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.pilot.Pilot;

/**
 * The command that checks a pilot's availability in the Wingman app.
 */
public class CheckPilotCommand implements Command {
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    /**
     * The UUID of the pilot whose availability is to be checked.
     */
    private final String id;

    /**
     * Creates a command that, when executed, checks the availability of the pilot with the given UUID.
     *
     * @param id the UUID of the pilot to be checked.
     */
    public CheckPilotCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isAvailable;

        int pilotId;
        try {
            pilotId = Command.parseIntegerToZeroBasedIndex(id);
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    id
            ));
        }

        boolean isPilotIndexValid = (pilotId < model.getPilotManager().size());
        if (!isPilotIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    pilotId + 1));
        }

        isAvailable = model.checkPilotByIndex(pilotId);

        Pilot pilot = model.getPilotManager().getItem(pilotId);
        if (isAvailable) {
            return new CommandResult(String.format(
                    "%s is available.",
                    pilot.toString()
            ));
        } else {
            return new CommandResult(String.format(
                    "%s is unavailable.",
                    pilot.toString()
            ));
        }
    }
}
