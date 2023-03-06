package seedu.address.logic.location.deletelocation;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.location.Location;

/**
 * The command that deletes a pilot from Wingman.
 */
public class DeleteLocationCommand implements Command {
    private final String uuid;
    private Optional<Location> locationToDelete;

    /**
     * Creates a command that deletes the location from the list.
     * @param uuid the uuid of the location to be deleted.
     */
    public DeleteLocationCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        locationToDelete = model.getLocationManager().getItem(uuid);
        model.deleteLocation(uuid);
        return new CommandResult("Deleted location: " + uuid);
    }
}
