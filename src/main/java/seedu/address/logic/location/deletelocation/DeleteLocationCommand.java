package seedu.address.logic.location.deletelocation;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.location.Location;

/**
 * The command that deletes a pilot from Wingman.
 */
public class DeleteLocationCommand implements Command {
    private final String id;
    private Optional<Location> locationToDelete;

    /**
     * Creates a command that deletes the location from the list.
     * @param id the uuid of the location to be deleted.
     */
    public DeleteLocationCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int index = Integer.parseInt(id);
        try {
            locationToDelete = model.getLocationManager().getItemByIndex(index);
            model.deleteLocationByIndex(index);
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        return new CommandResult("Deleted location: " + id);
    }
}
