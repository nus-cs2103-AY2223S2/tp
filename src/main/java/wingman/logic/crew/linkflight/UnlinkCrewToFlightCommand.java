package wingman.logic.crew.linkflight;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.crew.Crew;
import wingman.model.crew.FlightCrewType;
import wingman.model.flight.Flight;
import wingman.model.link.exceptions.LinkException;

/**
 * The command that unlinks a crew from a flight
 */
public class UnlinkCrewToFlightCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Unlinked %s from %s.";

    /**
     * The flight to be unlinked from.
     */
    private final Flight flight;

    /**
     * The id of the crews
     */
    private final Map<FlightCrewType, Crew> crews;

    /**
     * Creates a new unlink command.
     *
     * @param crews  the id of the crews.
     * @param flight the id of the flight.
     */
    public UnlinkCrewToFlightCommand(
            Flight flight,
            Map<FlightCrewType, Crew> crews
    ) {
        this.flight = flight;
        this.crews = crews;
    }

    @Override
    public String toString() {
        String result = crews.values()
                             .stream()
                             .map(crew -> String.format(
                                     "%s",
                                     crew.toString()
                             ))
                             .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, flight.getCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<FlightCrewType, Crew> entry : crews.entrySet()) {
                flight.crewLink.delete(entry.getKey(), entry.getValue());
                entry.getValue().setAvailable();
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(this.toString());
    }
}
