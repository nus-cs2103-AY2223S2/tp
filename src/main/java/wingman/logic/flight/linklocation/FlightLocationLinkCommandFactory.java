package wingman.logic.flight.linklocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import wingman.commons.fp.Lazy;
import wingman.commons.util.GetUtil;
import wingman.logic.core.Command;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.logic.toplevel.link.LinkFactoryBase;
import wingman.logic.toplevel.link.LinkFunction;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.flight.Flight;
import wingman.model.location.FlightLocationType;
import wingman.model.location.Location;

/**
 * The factory that creates {@code LinkLocationCommand}.
 */
public class FlightLocationLinkCommandFactory<T extends Command>
        extends LinkFactoryBase<T, Flight, Location, FlightLocationType> {
    public static final String COMMAND_WORD = "linklocation";
    public static final String FLIGHT_PREFIX = "/fl";
    public static final String LOCATION_DEPARTURE_PREFIX = "/from";
    public static final String LOCATION_ARRIVAL_PREFIX = "/to";

    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered.\n"
                    + "Please enter /fl followed by the flight ID.";
    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered.\n"
                    + "Please enter /from followed by the departure location ID "
                    + " and /to followed by the arrival location ID.";

    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private final FlightLocationLinkFunction<T> linkFunction;

    /**
     * Creates a new link command factory with the model registered.
     */
    public FlightLocationLinkCommandFactory(
            FlightLocationLinkFunction<T> linkFunction
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public FlightLocationLinkCommandFactory(
            Lazy<Model> modelLazy,
            FlightLocationLinkFunction<T> linkFunction
    ) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getFlightManager),
                linkFunction
        );
    }

    /**
     * Creates a new link location command factory with the given location manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param flightManagerLazy   the lazy instance of the flight manager.
     */
    public FlightLocationLinkCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy,
            FlightLocationLinkFunction<T> linkFunction
    ) {
        super(flightManagerLazy, locationManagerLazy);
        this.linkFunction = linkFunction;
    }

    public static FlightLocationLinkCommandFactory<LinkFlightToLocationCommand> linkFactory() {
        return new FlightLocationLinkCommandFactory<>(
                LinkFlightToLocationCommand::new);
    }

    public static FlightLocationLinkCommandFactory<UnlinkFlightToLocationCommand> unlinkFactory() {
        return new FlightLocationLinkCommandFactory<>(
                UnlinkFlightToLocationCommand::new);
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                FLIGHT_PREFIX,
                LOCATION_DEPARTURE_PREFIX,
                LOCATION_ARRIVAL_PREFIX
        ));
    }

    @Override
    public T createCommand(CommandParam param) throws ParseException,
                                                      CommandException {
        Optional<String> locationDepartureIdOptional =
                param.getNamedValues(LOCATION_DEPARTURE_PREFIX);

        Optional<String> locationArrivalIdOptional =
                param.getNamedValues(LOCATION_ARRIVAL_PREFIX);

        Flight flight = getSourceOrThrow(param.getNamedValues(FLIGHT_PREFIX));

        Map<FlightLocationType, Location> locationMap = new HashMap<>();

        boolean hasLocation = addTarget(
                locationDepartureIdOptional,
                FlightLocationType.LOCATION_DEPARTURE,
                locationMap
        ) || addTarget(
                locationArrivalIdOptional,
                FlightLocationType.LOCATION_ARRIVAL,
                locationMap
        );

        if (!hasLocation) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        return linkFunction.create(flight, locationMap);
    }

    @FunctionalInterface
    public interface FlightLocationLinkFunction<T extends Command>
            extends LinkFunction<T, Flight, Location, FlightLocationType> {
    }
}
