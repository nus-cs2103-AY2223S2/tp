package seedu.address.logic.crew.unlinkflight;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;
import seedu.address.model.crew.FlightCrewType;
import seedu.address.model.flight.Flight;
import seedu.address.model.link.exceptions.LinkException;

/**
 * The command that unlinks a crew from a flight
 */
public class UnlinkCrewToFlightCommand implements Command {
    private static final String FLIGHT_NOT_FOUND_EXCEPTION =
            "Flight with ID %s can't be found.";
    private static final String CREW_NOT_FOUND_EXCEPTION =
            "Crew with ID %s can't be found.";
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
     * @param crews the id of the crews.
     * @param flight the id of the flight.
     */
    public UnlinkCrewToFlightCommand(Flight flight, Map<FlightCrewType, Crew> crews) {
        this.flight = flight;
        this.crews = crews;
    }

    @Override
    public String toString() {
        String result = crews.entrySet()
                .stream()
                .map((entry) -> String.format(
                        "%s",
                        entry.getValue().toString()))
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
