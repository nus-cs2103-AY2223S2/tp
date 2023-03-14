package seedu.address.logic.location.linklocation;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;
import seedu.address.model.location.Location;


/**
 * The command that links departure and arrival locations to a flight.
 */
public class LinkLocationCommand implements Command {
    /**
     * The flight which the location is to be linked to.
     */
    private final String flightId;

    /**
     * The arrival location to link flight with.
     */
    private final String arrivalLocationId;

    /**
     * The departure location to link flight with.
     */
    private final String departureLocationId;


    /**
     * Creates a command to link location to a flight.
     * @param flightId the id of the flight
     * @param arrivalLocationId the id of the arrival location
     * @param departureLocationId the id of the departure location
     */
    public LinkLocationCommand(String flightId, String departureLocationId, String arrivalLocationId) {
        this.flightId = flightId;
        this.departureLocationId = departureLocationId;
        this.arrivalLocationId = arrivalLocationId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Flight flightToLink = model.getFlightById(flightId);
        Location departureLocationToLink = model.getLocationById(departureLocationId);
        Location arrivalLocationToLink = model.getLocationById(arrivalLocationId);
        model.linkFlightToLocations(flightToLink, departureLocationToLink, arrivalLocationToLink);
        return new CommandResult(
                String.format("Linked departure location %s and arrival location %s to flight %s.",
                        departureLocationId,
                        arrivalLocationId,
                        flightId)
        );
    }
}
