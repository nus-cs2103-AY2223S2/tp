package wingman.logic.plane.linkflight;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.flight.Flight;
import wingman.model.link.exceptions.LinkException;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;


/**
 * The command that links a plane to a flight
 */
public class LinkPlaneToFlightCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Linked %s to %s.";

    /**
     * The id of the flight
     */
    private final Flight flight;

    /**
     * The id of the plane
     */
    private final Map<FlightPlaneType, Plane> planes;

    /**
     * Creates a new link command.
     *
     * @param flight the id of the flight.
     * @param planes the id of the planes.
     */
    public LinkPlaneToFlightCommand(
            Flight flight,
            Map<FlightPlaneType, Plane> planes
    ) {
        this.flight = flight;
        this.planes = planes;
    }

    @Override
    public String toString() {
        String result = planes.entrySet()
                              .stream()
                              .map((entry) -> String.format(
                                      "%s %s",
                                      entry.getKey(),
                                      entry.getValue().getModel()
                              ))
                              .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, flight.getCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<FlightPlaneType, Plane> entry : planes.entrySet()) {
                flight.planeLink.putRevolve(entry.getKey(), entry.getValue());
                entry.getValue().setUnavailable();
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(this.toString());
    }
}
