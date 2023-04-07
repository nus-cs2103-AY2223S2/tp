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
    public static final String LINK_COMMAND_WORD = "linklocation";
    public static final String UNLINK_COMMAND_WORD = "unlinklocation";
    public static final String FLIGHT_PREFIX = "/fl";
    public static final String LOCATION_DEPARTURE_PREFIX = "/from";
    public static final String LOCATION_ARRIVAL_PREFIX = "/to";

    private static final String NO_LOCATION_MESSAGE =
            "%s: No location has been entered.\n"
                    + "Please enter /from followed by the departure location ID "
                    + " and /to followed by the arrival location ID.\n"
                    + "Command format: %s";

    private static final String COMMAND_FORMAT =
            "%s /fl flight-id /from location-id /to location-id";

    private final FlightLocationLinkFunction<T> linkFunction;
    private final String commandWord;

    /**
     * Creates a new link command factory with the model registered.
     */
    public FlightLocationLinkCommandFactory(
            FlightLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction, commandWord);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public FlightLocationLinkCommandFactory(
            Lazy<Model> modelLazy,
            FlightLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getFlightManager),
                linkFunction,
                commandWord
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
            FlightLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        super(flightManagerLazy, locationManagerLazy);
        this.linkFunction = linkFunction;
        this.commandWord = commandWord;
    }

    /**
     * Returns a new link command factory.
     *
     * @return a new link command factory.
     */
    public static FlightLocationLinkCommandFactory<LinkFlightToLocationCommand> linkFactory() {
        return new FlightLocationLinkCommandFactory<>(
                LinkFlightToLocationCommand::new, LINK_COMMAND_WORD);
    }

    /**
     * Returns a new unlink command factory.
     *
     * @return a new unlink command factory.
     */
    public static FlightLocationLinkCommandFactory<UnlinkFlightToLocationCommand> unlinkFactory() {
        return new FlightLocationLinkCommandFactory<>(
                UnlinkFlightToLocationCommand::new, UNLINK_COMMAND_WORD);
    }

    @Override
    public String getCommandWord() {
        return commandWord;
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

        Flight flight = getSourceOrThrow(
                FLIGHT_PREFIX,
                param.getNamedValues(FLIGHT_PREFIX)
        );

        Map<FlightLocationType, Location> locationMap = new HashMap<>();

        boolean hasLocation = addTarget(
                LOCATION_DEPARTURE_PREFIX,
                locationDepartureIdOptional,
                FlightLocationType.LOCATION_DEPARTURE,
                locationMap
        ) | addTarget(
                LOCATION_ARRIVAL_PREFIX,
                locationArrivalIdOptional,
                FlightLocationType.LOCATION_ARRIVAL,
                locationMap
        );

        if (!hasLocation) {
            throw ParseException.formatted(
                    NO_LOCATION_MESSAGE,
                    commandWord,
                    getCommandFormatHint()
            );
        }

        return linkFunction.apply(flight, locationMap);
    }

    @Override
    protected String getCommandFormatHint() {
        return String.format(
                COMMAND_FORMAT,
                commandWord
        );
    }

    /**
     * The functional interface that creates a new link command.
     *
     * @param <T> the type of the link command.
     */
    @FunctionalInterface
    public interface FlightLocationLinkFunction<T extends Command>
            extends LinkFunction<T, Flight, Location, FlightLocationType> {
    }
}
