package seedu.address.logic.flight.deleteflight;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;

import java.util.Optional;

/**
 * The command that deletes a flight from Wingman
 */
public class DeleteFlightCommand implements Command {
    /**
     * The UUID of the flight to be deleted
     */
    private final String uuid;

    /**
     * This can be used to undo the deletion.
     */
    private Optional<Flight> flightToDelete;

    /**
     * Creates a command that, when executed, deletes the flight with the
     * given uuid.
     *
     * @param uuid The index of the flight to be deleted.
     */
    public DeleteFlightCommand(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        flightToDelete = model.getFlightManager().getItem(uuid);
        model.deleteFlight(uuid);
        return new CommandResult("Deleted flight: " + uuid);
    }
}
