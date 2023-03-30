package wingman.logic.crew.checkcrew;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.crew.Crew;

/**
 * The command that checks a crew's availability in the Wingman app.
 */
public class CheckCrewCommand implements Command {
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";

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

        // checking if its a valid integer
        int crewId;
        try {
            crewId = Command.parseIntegerToZeroBasedIndex(id);
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    id
            ));
        }

        // checking if its a vaid index
        boolean isCrewIndexValid = (crewId < model.getCrewManager().size());
        if (!isCrewIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    crewId + 1));
        }

        isAvailable = model.checkCrewByIndex(crewId);

        Crew crew = model.getCrewManager().getItem(crewId);
        if (isAvailable) {
            return new CommandResult(String.format(
                    "%s is available.",
                    crew.toString()
            ));
        } else {
            return new CommandResult(String.format(
                    "%s is unavailable.",
                    crew.toString()
            ));
        }
    }
}
