package seedu.address.logic.plane.unlinkflight;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.fp.Lazy;
import seedu.address.commons.util.GetUtil;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.flight.Flight;
import seedu.address.model.plane.FlightPlaneType;
import seedu.address.model.plane.Plane;


/**
 * The factory that creates {@code UnlinkPlaneCommand}.
 */
public class UnlinkPlaneToFlightCommandFactory implements CommandFactory<UnlinkPlaneToFlightCommand> {
    private static final String COMMAND_WORD = "unlinkflight";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String PLANE_USING_PREFIX = "/pl";

    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered.\n"
                    + "Please enter /fl followed by the flight ID.";
    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered.\n"
                    + "Please enter /pl followed by the plane ID.";
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;
    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy;

    /**
     * Creates a new unlink command factory with the model registered.
     */
    public UnlinkPlaneToFlightCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the unlink command factory.
     */
    public UnlinkPlaneToFlightCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getFlightManager),
                modelLazy.map(Model::getPlaneManager)
        );
    }

    /**
     * Creates a new unlink plane command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param flightManagerLazy the lazy instance of the flight manager.
     * @param planeManagerLazy  the lazy instance of the plane manager.
     */
    public UnlinkPlaneToFlightCommandFactory(
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy,
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy
    ) {
        this.flightManagerLazy = flightManagerLazy;
        this.planeManagerLazy = planeManagerLazy;
    }

    /**
     * Creates a new unlink plane command factory with the given plane manager
     * and the flight manager.
     *
     * @param flightManager the flight manager.
     * @param planeManager  the plane manager.
     */
    public UnlinkPlaneToFlightCommandFactory(
            ReadOnlyItemManager<Flight> flightManager,
            ReadOnlyItemManager<Plane> planeManager
    ) {
        this(Lazy.of(flightManager), Lazy.of(planeManager));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                FLIGHT_PREFIX,
                PLANE_USING_PREFIX
        ));
    }

    private boolean addPlane(Optional<String> planeIdOptional, FlightPlaneType type, Map<FlightPlaneType, Plane> target)
            throws CommandException {
        if (planeIdOptional.isEmpty()) {
            return false;
        }

        int planeId;
        try {
            planeId = Integer.parseInt(planeIdOptional.get());
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    planeIdOptional.get()
            ));
        }

        boolean isCrewIndexValid = (planeId < planeManagerLazy.get().size());
        if (!isCrewIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    planeId));
        }

        Optional<Plane> planeOptional = planeManagerLazy.get().getItemOptional(planeId);
        if (planeOptional.isEmpty()) {
            return false;
        }
        target.put(type, planeOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(Optional<String> flightIdOptional) throws ParseException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        int flightId;
        try {
            flightId = Integer.parseInt(flightIdOptional.get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    flightIdOptional.get()
            ));
        }

        boolean isFlightIndexValid = (flightId < flightManagerLazy.get().size());
        if (!isFlightIndexValid) {
            throw new ParseException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    flightId));
        }

        Optional<Flight> flightOptional = flightManagerLazy.get().getItemOptional(flightId);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }

        return flightOptional.get();
    }

    @Override
    public UnlinkPlaneToFlightCommand createCommand(
            CommandParam param
    ) throws ParseException, IndexOutOfBoundException {
        Optional<String> planeUsingIdOptional =
                param.getNamedValues(PLANE_USING_PREFIX);

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        Map<FlightPlaneType, Plane> planes = new HashMap<>();

        boolean hasFoundPlane;
        try {
            hasFoundPlane = addPlane(
                    planeUsingIdOptional,
                    FlightPlaneType.PLANE_USING,
                    planes
            );
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        if (!hasFoundPlane) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        return new UnlinkPlaneToFlightCommand(flight, planes);
    }
}
