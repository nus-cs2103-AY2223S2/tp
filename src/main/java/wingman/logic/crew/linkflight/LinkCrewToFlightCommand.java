package wingman.logic.crew.linkflight;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.crew.Crew;
import wingman.model.crew.FlightCrewType;
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.flight.Flight;
import wingman.model.link.exceptions.LinkException;

/**
 * The command that links a crew to a flight
 */
public class LinkCrewToFlightCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Linked %s to %s.";

    /**
     * The flight to be linked to.
     */
    private final Flight flight;

    /**
     * The crew to be linked.
     */
    private final Map<FlightCrewType, Crew> crews;

    /**
     * Creates a new link command.
     *
     * @param crews  the id of the crews.
     * @param flight the id of the flight.
     */
    public LinkCrewToFlightCommand(
            Flight flight,
            Map<FlightCrewType, Crew> crews
    ) {
        this.flight = flight;
        this.crews = crews;
    }

    @Override
    public String toString() {
        String result = crews.entrySet()
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
            for (Map.Entry<FlightCrewType, Crew> entry : crews.entrySet()) {
                flight.crewLink.putRevolve(entry.getKey(), entry.getValue());
                entry.getValue().setUnavailable();
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        } catch (IndexOutOfBoundException e) {
            return new CommandResult(
                    String.format("Error: %s", e.getMessage())
            );
        }
        return new CommandResult(this.toString());
    }
}
