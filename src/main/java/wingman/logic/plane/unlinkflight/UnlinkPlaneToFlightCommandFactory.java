package wingman.logic.plane.unlinkflight;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import wingman.commons.fp.Lazy;
import wingman.commons.util.GetUtil;
import wingman.logic.core.Command;
import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.flight.Flight;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;


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

    private boolean addPlane(
            Optional<String> planeIdOptional,
            FlightPlaneType type,
            Map<FlightPlaneType, Plane> target
    ) throws CommandException {
        if (planeIdOptional.isEmpty()) {
            return false;
        }

        int planeId;
        try {
            planeId = Command.parseIntegerToZeroBasedIndex(planeIdOptional.get());
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
                    planeId + 1));
        }

        Optional<Plane> planeOptional = planeManagerLazy.get().getItemOptional(planeId);
        if (planeOptional.isEmpty()) {
            return false;
        }
        target.put(type, planeOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(
            Optional<String> flightIdOptional
    ) throws ParseException, CommandException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        int flightId;
        try {
            flightId = Command.parseIntegerToZeroBasedIndex(flightIdOptional.get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    flightIdOptional.get()
            ));
        }

        boolean isFlightIndexValid = (flightId < flightManagerLazy.get().size());
        if (!isFlightIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    flightId + 1));
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
    ) throws ParseException, IndexOutOfBoundException, CommandException {
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
