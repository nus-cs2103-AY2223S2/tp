package seedu.address.logic.plane.unlinkplane;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;

/**
 * The command that unlinks a plane from a flight in the address book.
 */
public class UnlinkPlaneCommand implements Command {
    /**
     * The flight which the plane is being unlinked from.
     */
    private final Flight flight;

    /**
     * Creates a command that, when executed, resets the linked plane from a flight in the address book.
     *
     * @param flight The flight to be unlinked from.
     */
    public UnlinkPlaneCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: unlink to the flight based on flight's implementation
        return new CommandResult(flight.toString() + " no longer has a linked plane.");
    }
}
