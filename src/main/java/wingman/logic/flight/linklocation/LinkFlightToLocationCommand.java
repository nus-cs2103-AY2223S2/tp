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
 * The command that links locations to a flight.
 */
public class LinkFlightToLocationCommand implements Command {
    private static final String DISPLAY_MESSAGE = "Linked %s to %s.";

    /**
     * The flight to be linked.
     */
    private final Flight flight;

    /**
     * A map of the locations to be linked to and the types of the links to be made.
     */
    private final Map<FlightLocationType, Location> locations;

    /**
     * Creates a new link command.
     *
     * @param locations a map storing the id of the locations and their type of link to this flight.
     * @param flight    the id of the flight.
     */
    public LinkFlightToLocationCommand(
            Flight flight,
            Map<FlightLocationType, Location> locations
    ) {
        this.flight = flight;
        this.locations = locations;
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
                flight.setLocation(
                        entry.getKey(),
                        entry.getValue()
                );
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }
}
