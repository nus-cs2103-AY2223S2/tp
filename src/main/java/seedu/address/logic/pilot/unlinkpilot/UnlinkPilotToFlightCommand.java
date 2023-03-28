package seedu.address.logic.pilot.unlinkpilot;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;
import seedu.address.model.link.exceptions.LinkException;
import seedu.address.model.pilot.FlightPilotType;
import seedu.address.model.pilot.Pilot;

/**
 * The command that unlinks a pilot from a flight
 */
public class UnlinkPilotToFlightCommand implements Command {
    private static final String FLIGHT_NOT_FOUND_EXCEPTION =
            "Flight with id %s is not found.";
    private static final String PILOT_NOT_FOUND_EXCEPTION =
            "Pilot with id %s is not found.";
    private static final String DISPLAY_MESSAGE =
            "Unlinked pilot %s from flight %s.";

    /**
     * The id of the flight
     */
    private final Flight flight;

    /**
     * The id of the pilot
     */
    private final Map<FlightPilotType, Pilot> pilots;

    /**
     * Creates a new link command.
     *
     * @param flight the id of the flight.
     * @param pilots the id of the pilots.
     */
    public UnlinkPilotToFlightCommand(Flight flight, Map<FlightPilotType, Pilot> pilots) {
        this.flight = flight;
        this.pilots = pilots;
    }

    @Override
    public String toString() {
        String result = pilots.entrySet()
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
            for (Map.Entry<FlightPilotType, Pilot> entry : pilots.entrySet()) {
                flight.pilotLink.delete(entry.getKey(), entry.getValue());
                entry.getValue().setAvailable();
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(this.toString());
    }
}
