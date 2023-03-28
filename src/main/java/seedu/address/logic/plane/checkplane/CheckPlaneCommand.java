package seedu.address.logic.plane.checkplane;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plane.Plane;

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
        int index = Integer.parseInt(id);
        if (index >= model.getPlaneManager().size() || index < 0) {
            throw new CommandException(String.format(
                    "Index %s is out of bounds."
                            + "Please enter a valid index.",
                    index
            ));
        } else {
            boolean isAvailable = model.checkPlaneByIndex(index);
            Plane plane = model.getPlaneManager().getItem(index);

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
}
