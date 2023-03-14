package seedu.address.logic.plane.addplane;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plane.Plane;

/**
 * The command that adds a plane to the Wingman app.
 */
public class AddPlaneCommand implements Command {
    /**
     * The Plane to be added.
     */
    private final Plane plane;

    /**
     * Creates a command that, when executed, adds the plane to the address
     * book.
     *
     * @param plane The pilot to be added.
     */
    public AddPlaneCommand(Plane plane) {
        this.plane = plane;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addPlane(plane);
        return new CommandResult("Added plane: " + plane.toString());
    }
}
