package seedu.address.logic.plane.linkplane;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plane.Plane;
import seedu.address.model.flight.Flight;

/**
 * The command that links a plane to a flight in the address book.
 */
public class LinkPlaneCommand implements Command {
    /**
     * The flight which the plane is being linked to.
     */
    private final Flight flight;

    /**
     * The plane to be linked to the flight.
     */
    private final Plane plane;

    /**
     * Creates a command that, when executed, links the plane to a flight in the address book.
     *
     * @param flight The flight to be linked to.
     * @param plane  The plane to be linked.
     */
    public LinkPlaneCommand(Flight flight, Plane plane) {
        this.flight = flight;
        this.plane = plane;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO: link to the flight based on flight's implementation
        return new CommandResult("Linked plane: " + plane.toString() + " to " + flight.toString());
    }
}
