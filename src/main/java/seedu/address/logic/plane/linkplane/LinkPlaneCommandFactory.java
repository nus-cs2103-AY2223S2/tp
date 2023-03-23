package seedu.address.logic.plane.linkplane;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.fp.Lazy;
import seedu.address.commons.util.GetUtil;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.flight.Flight;
import seedu.address.model.plane.FlightPlaneType;
import seedu.address.model.plane.Plane;

/**
 * The factory that creates {@code LinkPlaneCommand}.
 */
public class LinkPlaneCommandFactory implements CommandFactory<LinkPlaneCommand> {
    private static final String COMMAND_WORD = "link";
    private static final String PLANE_USING_PREFIX = "/pu";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered. Please enter /pu for the plane being used in this link.";
    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered. Please enter /fl for the flight.";

    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy;

    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     */
    public LinkPlaneCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public LinkPlaneCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getPlaneManager),
                modelLazy.map(Model::getFlightManager)
        );
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param planeManagerLazy  the lazy instance of the plane manager.
     * @param flightManagerLazy the lazy instance of the flight manager.
     */
    public LinkPlaneCommandFactory(
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy
    ) {
        this.planeManagerLazy = planeManagerLazy;
        this.flightManagerLazy = flightManagerLazy;
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * and the flight manager.
     *
     * @param planeManager  the plane manager.
     * @param flightManager the flight manager.
     */
    public LinkPlaneCommandFactory(
            ReadOnlyItemManager<Plane> planeManager,
            ReadOnlyItemManager<Flight> flightManager
    ) {
        this(Lazy.of(planeManager), Lazy.of(flightManager));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                PLANE_USING_PREFIX,
                FLIGHT_PREFIX
        ));
    }


    private boolean addPlane(
            Optional<String> planeIdOptional,
            FlightPlaneType type,
            Map<FlightPlaneType, Plane> target
    ) {
        if (planeIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPlane =
                Integer.parseInt(planeIdOptional.get());
        Optional<Plane> planeOptional =
                planeManagerLazy.get().getItemByIndex(indexOfPlane);
        if (planeOptional.isEmpty()) {
            return false;
        }
        target.put(type, planeOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(
            Optional<String> flightIdOptional
    ) throws ParseException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        int indexOfFlight =
                Integer.parseInt(flightIdOptional.get());
        Optional<Flight> flightOptional =
                flightManagerLazy.get().getItemByIndex(indexOfFlight);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        return flightOptional.get();
    }


    @Override
    public LinkPlaneCommand createCommand(CommandParam param) throws ParseException, IndexOutOfBoundException {
        Optional<String> planeUsingIdOptional =
                param.getNamedValues(PLANE_USING_PREFIX);
        Map<FlightPlaneType, Plane> planes = new HashMap<>();

        boolean hasFoundPlane = addPlane(
                planeUsingIdOptional,
                FlightPlaneType.PLANE_USING,
                planes
        );

        if (!hasFoundPlane) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        return new LinkPlaneCommand(planes, flight);
    }
}
