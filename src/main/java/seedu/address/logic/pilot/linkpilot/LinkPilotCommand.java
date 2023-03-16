package seedu.address.logic.pilot.linkpilot;

import java.util.Optional;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;

/**
 * The command that links the pilot.
 */
public class LinkPilotCommand implements Command {
    private static final String FLIGHT_NOT_FOUND_EXCEPTION =
        "Flight with id %s is not found.";

    /**
     * The id of the pilot
     */
    private final String pilotId;

    /**
     * The id of the flight
     */
    private final String flightId;

    /**
     * Creates a new link command.
     *
     * @param pilotId  the id of the pilot.
     * @param flightId the id of the flight.
     */
    public LinkPilotCommand(String pilotId, String flightId) {
        this.pilotId = pilotId;
        this.flightId = flightId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Optional<Flight> flightOptional = model.getFlightManager().getItem(flightId);
        if (flightOptional.isEmpty()) {
            throw new CommandException(String.format(FLIGHT_NOT_FOUND_EXCEPTION, flightId));
        }
        Flight flight = flightOptional.get();
        return new CommandResult("Not Implemented Yet");
    }
}
