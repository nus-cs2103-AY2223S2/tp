package wingman.logic.flight.linklocation;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.flight.Flight;
import wingman.model.link.exceptions.LinkException;
import wingman.model.location.FlightLocationType;
import wingman.model.location.Location;

/**
 * The command that unlinks locations from flights.
 */
public class UnlinkFlightToLocationCommand implements Command {
    private static final String DISPLAY_MESSAGE = "Unlinked %s from %s.";

    /**
     * The flight to be unlinked.
     */
    private final Flight flight;

    /**
     * The locations to be unlinked from.
     */
    private final Map<FlightLocationType, Location> locations;

    /**
     * Creates a new unlink command.
     *
     * @param locations the id of the locations.
     * @param flight    the id of the flight.
     */
    public UnlinkFlightToLocationCommand(
            Flight flight,
            Map<FlightLocationType, Location> locations
    ) {
        this.locations = locations;
        this.flight = flight;
    }

    @Override
    public String toString() {
        String result = locations.values()
                                 .stream()
                                 .map(location -> String.format(
                                         "%s",
                                         location.toString()
                                 ))
                                 .collect(Collectors.joining(", "));
        return String.format(DISPLAY_MESSAGE, result, flight.getCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<FlightLocationType, Location> entry : locations.entrySet()) {
                flight.removeLocation(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }
}
