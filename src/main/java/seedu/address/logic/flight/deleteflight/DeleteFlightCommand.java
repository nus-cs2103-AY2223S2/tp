package seedu.address.logic.flight.deleteflight;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.flight.Flight;

/**
 * The command that deletes a flight from Wingman
 */
public class DeleteFlightCommand implements Command {
    /**
     * The UUID of the flight to be deleted
     */
    private final String id;

    /**
     * This can be used to undo the deletion.
     */
    private Optional<Flight> flightToDelete;

    /**
     * Creates a command that, when executed, deletes the flight with the
     * given uuid.
     *
     * @param id The index of the flight to be deleted.
     */
    public DeleteFlightCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int index = Integer.parseInt(id);
        try {
            flightToDelete = model.getFlightManager().getItemByIndex(index);
            model.deleteFlightByIndex(index);
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        return new CommandResult("Deleted flight: " + id);
    }
}
