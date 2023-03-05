package seedu.address.logic.location.addlocation;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.location.Location;

/**
 * The command that adds a location to Wingman.
 */
public class AddLocationCommand implements Command {
    private final Location location;

    /**
     * Creates a executable command that add a location to the list.
     * @param location the location to add.
     */
    public AddLocationCommand(Location location) {
        this.location = location;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addLocation(location);
        return new CommandResult("Added location: " + location);
    }

}
