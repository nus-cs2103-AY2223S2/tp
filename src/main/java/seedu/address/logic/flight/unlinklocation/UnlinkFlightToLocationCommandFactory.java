package seedu.address.logic.flight.unlinklocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.fp.Lazy;
import seedu.address.commons.util.GetUtil;
import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.flight.Flight;
import seedu.address.model.location.FlightLocationType;
import seedu.address.model.location.Location;

/**
 * The factory that creates {@code UnlinkLocationCommand}.
 */
public class UnlinkFlightToLocationCommandFactory implements CommandFactory<UnlinkFlightToLocationCommand> {
    public static final String COMMAND_WORD = "unlinklocation";
    public static final String FLIGHT_PREFIX = "/fl";
    public static final String LOCATION_DEPARTURE_PREFIX = "/from";
    public static final String LOCATION_ARRIVAL_PREFIX = "/to";

    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered. Please enter /fl for the flight.";
    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered. Please enter /from for the departure location"
                    + " and /to for the arrival location.";

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new unlink command factory with the model registered.
     */
    public UnlinkFlightToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public UnlinkFlightToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getFlightManager)
        );
    }

    /**
     * Creates a new unlink location command factory with the given location manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param flightManagerLazy   the lazy instance of the flight manager.
     */
    public UnlinkFlightToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy
    ) {
        this.locationManagerLazy = locationManagerLazy;
        this.flightManagerLazy = flightManagerLazy;
    }

    /**
     * Creates a new unlink location command factory with the given location manager
     * and the flight manager.
     *
     * @param locationManager the location manager.
     * @param flightManager   the flight manager.
     */
    public UnlinkFlightToLocationCommandFactory(
            ReadOnlyItemManager<Location> locationManager,
            ReadOnlyItemManager<Flight> flightManager
    ) {
        this(Lazy.of(locationManager), Lazy.of(flightManager));
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

    private boolean addLocation(
            Optional<String> locationIdOptional,
            FlightLocationType type,
            Map<FlightLocationType, Location> target
    ) throws CommandException {
        if (locationIdOptional.isEmpty()) {
            return false;
        }
        int indexOfLocation =
                Command.parseIntegerToZeroBasedIndex(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemOptional(indexOfLocation);
        if (locationOptional.isEmpty()) {
            return false;
        }
        target.put(type, locationOptional.get());

        return true;
    }

    private Flight getFlightOrThrow(
            Optional<String> flightIdOptional
    ) throws ParseException, CommandException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        int indexOfFlight =
                Command.parseIntegerToZeroBasedIndex(flightIdOptional.get());
        Optional<Flight> flightOptional =
                flightManagerLazy.get().getItemOptional(indexOfFlight);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }

        return flightOptional.get();
    }

    @Override
    public UnlinkFlightToLocationCommand createCommand(
            CommandParam param
    ) throws ParseException, IndexOutOfBoundException, CommandException {
        Optional<String> locationDepartureIdOptional =
                param.getNamedValues(LOCATION_DEPARTURE_PREFIX);
        Optional<String> locationArrivalIdOptional =
                param.getNamedValues(LOCATION_ARRIVAL_PREFIX);

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        Map<FlightLocationType, Location> locations = new HashMap<>();

        boolean hasFoundLocation = addLocation(
                locationDepartureIdOptional,
                FlightLocationType.LOCATION_DEPARTURE,
                locations
        ) || addLocation(
                locationArrivalIdOptional,
                FlightLocationType.LOCATION_ARRIVAL,
                locations
        );

        if (!hasFoundLocation) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        return new UnlinkFlightToLocationCommand(locations, flight);
    }
}
