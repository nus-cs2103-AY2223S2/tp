package seedu.address.logic.plane.unlinkplane;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;

import seedu.address.model.ModelManager;
import seedu.address.model.flight.Flight;


/**
 * The factory that's responsible for creating a {@code UnlinkPlaneCommand}.
 */
public class UnlinkPlaneCommandFactory implements CommandFactory<UnlinkPlaneCommand> {
    public static final String COMMAND_WORD = "unlink";
    public static final String PREFIX_FLIGHT_ID = "/flight";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_FLIGHT_ID));
    }

    @Override
    public UnlinkPlaneCommand createCommand(CommandParam param) throws ParseException {
        String flightId = param.getNamedValuesOrThrow(PREFIX_FLIGHT_ID);

        ModelManager modelManager = new ModelManager();
        Flight flight = null;
        for (Flight f : modelManager.getFilteredFlightList()) {
            if (f.getId().equals(flightId)) {
                flight = f;
                break;
            }
        }

        // TODO: exception when the given id doesn't correspond to any existing flight
        return new UnlinkPlaneCommand(flight);
    }
}
