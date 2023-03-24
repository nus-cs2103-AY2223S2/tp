package seedu.address.logic.flight.linklocation;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;
import seedu.address.model.link.exceptions.LinkException;
import seedu.address.model.location.FlightLocationType;
import seedu.address.model.location.Location;

/**
 * The command that links locations to a flight.
 */
public class LinkFlightToLocationCommand implements Command {
    private static final String FLIGHT_NOT_FOUND_EXCEPTION =
            "Flight with id %s is not found.";
    private static final String LOCATION_NOT_FOUND_EXCEPTION =
            "Location with id %s is not found.";
    private static final String DISPLAY_MESSAGE =
            "Linked %s to flight %s.";

    /**
     * The id of the location
     */
    private final Map<FlightLocationType, Location> locations;

    /**
     * The id of the flight
     */
    private final Flight flight;

    /**
     * Creates a new link command.
     *
     * @param locations the id of the locations.
     * @param flight the id of the flight.
     */
    public LinkFlightToLocationCommand(Map<FlightLocationType, Location> locations, Flight flight) {
        this.locations = locations;
        this.flight = flight;
    }

    @Override
    public String toString() {
        String result = locations.entrySet()
                .stream()
                .map((entry) -> String.format(
                        "%s: %s",
                        entry.getKey(),
                        entry.getValue().getName()))
                .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, flight.getCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<FlightLocationType, Location> entry : locations.entrySet()) {
                flight.locationLink.putRevolve(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }
}
