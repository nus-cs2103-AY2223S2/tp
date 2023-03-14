package seedu.address.logic.plane.linkplane;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;

import seedu.address.model.ModelManager;
import seedu.address.model.flight.Flight;
import seedu.address.model.plane.Plane;


/**
 * The factory that's responsible for creating a {@code LinkPlaneCommand}.
 */
public class LinkPlaneCommandFactory implements CommandFactory<LinkPlaneCommand> {
    public static final String COMMAND_WORD = "link";
    public static final String PREFIX_FLIGHT_ID = "/flight";
    public static final String PREFIX_PLANE_ID = "/plane";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_FLIGHT_ID, PREFIX_PLANE_ID));
    }

    @Override
    public LinkPlaneCommand createCommand(CommandParam param) throws ParseException {
        String flightId = param.getNamedValuesOrThrow(PREFIX_FLIGHT_ID);
        String planeId = param.getNamedValuesOrThrow(PREFIX_PLANE_ID);

        ModelManager modelManager = new ModelManager();
        Flight flight = null;
        Plane plane = null;

        for (Flight f : modelManager.getFilteredFlightList()) {
            if (f.getId().equals(flightId)) {
                flight = f;
                break;
            }
        }

        for (Plane p : modelManager.getFilteredPlaneList()) {
            if (p.getId().equals(planeId)) {
                plane = p;
                break;
            }
        }

        // TODO: exception when the given id doesn't correspond to any existing flight
        return new LinkPlaneCommand(flight, plane);
    }
}
