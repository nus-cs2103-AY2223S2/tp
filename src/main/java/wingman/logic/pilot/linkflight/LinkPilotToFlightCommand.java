package wingman.logic.pilot.linkflight;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.flight.Flight;
import wingman.model.link.exceptions.LinkException;
import wingman.model.pilot.FlightPilotType;
import wingman.model.pilot.Pilot;

/**
 * The command that links the pilot.
 */
public class LinkPilotToFlightCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Linked %s to %s.";

    /**
     * The id of the flight.
     */
    private final Flight flight;

    /**
     * The id of the pilot.
     */
    private final Map<FlightPilotType, Pilot> pilots;

    /**
     * Creates a new link command.
     *
     * @param flight the id of the flight.
     * @param pilots the id of the pilots.
     */
    public LinkPilotToFlightCommand(
            Flight flight,
            Map<FlightPilotType, Pilot> pilots
    ) {
        this.flight = flight;
        this.pilots = pilots;
    }

    @Override
    public String toString() {
        String result = pilots.entrySet()
                              .stream()
                              .map((entry) -> String.format(
                                      "%s %s",
                                      entry.getKey(),
                                      entry.getValue().getName()
                              ))
                              .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, flight.getCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<FlightPilotType, Pilot> entry : pilots.entrySet()) {
                flight.pilotLink.putRevolve(entry.getKey(), entry.getValue());
                entry.getValue().setUnavailable();
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(this.toString());
    }
}
