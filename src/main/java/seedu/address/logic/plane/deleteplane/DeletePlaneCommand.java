package seedu.address.logic.plane.deleteplane;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plane.Plane;

/**
 * The command that deletes a plane from the Wingman app.
 */
public class DeletePlaneCommand implements Command {
    /**
     * The UUID of the plane to be deleted.
     */
    private final String uuid;

    /**
     * This can be used to undo the deletion.
     */
    private Optional<Plane> planeToDelete;

    /**
     * Creates a command that, when executed, deletes the plane with the
     * given UUID.
     *
     * @param uuid The index of the plane to be deleted.
     */
    public DeletePlaneCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        planeToDelete = model.getPlaneManager().getItem(uuid);
        model.deletePlane(uuid);
        return new CommandResult("Deleted plane: " + uuid);
    }
}
