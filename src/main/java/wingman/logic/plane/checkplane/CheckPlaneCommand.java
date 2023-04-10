package wingman.logic.plane.checkplane;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.plane.Plane;

/**
 * The command that checks a plane's availability in the Wingman app.
 */
public class CheckPlaneCommand implements Command {
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";

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
        boolean isAvailable;

        int planeId;
        try {
            planeId = Command.parseIntegerToZeroBasedIndex(id);
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    id
            ));
        }

        boolean isPlaneIndexValid = (planeId < model.getPlaneManager().size());
        if (!isPlaneIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    planeId + 1));
        }

        isAvailable = model.checkPlaneByIndex(planeId);
        Plane plane = model.getPlaneManager().getItem(planeId);
        if (isAvailable) {
            return new CommandResult(String.format(
                    "%s is available.",
                    plane.toString()
            ));
        } else {
            return new CommandResult(String.format(
                    "%s is unavailable.",
                    plane.toString()
            ));
        }
    }
}
