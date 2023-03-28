package seedu.address.logic.flight.linklocation;

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
import seedu.address.model.location.FlightLocationType;
import seedu.address.model.location.Location;

/**
 * The factory that creates {@code LinkLocationCommand}.
 */
public class LinkFlightToLocationCommandFactory implements CommandFactory<LinkFlightToLocationCommand> {
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

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;
    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     */
    public LinkFlightToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public LinkFlightToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getFlightManager)
        );
    }

    /**
     * Creates a new link location command factory with the given location manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param flightManagerLazy   the lazy instance of the flight manager.
     */
    public LinkFlightToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy
    ) {
        this.locationManagerLazy = locationManagerLazy;
        this.flightManagerLazy = flightManagerLazy;
    }

    /**
     * Creates a new link location command factory with the given location manager
     * and the flight manager.
     *
     * @param locationManager the location manager.
     * @param flightManager   the flight manager.
     */
    public LinkFlightToLocationCommandFactory(
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
            Map<FlightLocationType, Location> target)
            throws CommandException {
        if (locationIdOptional.isEmpty()) {
            return false;
        }

        int locationId;
        try {
            locationId = Integer.parseInt(locationIdOptional.get());
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    locationIdOptional.get()
            ));
        }

        boolean isLocationIndexValid = (locationId < locationManagerLazy.get().size());
        if (!isLocationIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    locationId));
        }

        Optional<Location> locationOptional = locationManagerLazy.get().getItemOptional(locationId);
        if (locationOptional.isEmpty()) {
            return false;
        }
        target.put(type, locationOptional.get());
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
            throw new ParseException(String.format(INVALID_INDEX_VALUE_MESSAGE, flightIdOptional.get()));
        }

        boolean isFlightIndexValid = (flightId < locationManagerLazy.get().size());
        if (!isFlightIndexValid) {
            throw new ParseException(String.format(INDEX_OUT_OF_BOUNDS_MESSAGE, flightId));
        }

        Optional<Flight> flightOptional = flightManagerLazy.get().getItemOptional(flightId);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        return flightOptional.get();
    }

    @Override
    public LinkFlightToLocationCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> locationDepartureIdOptional =
                param.getNamedValues(LOCATION_DEPARTURE_PREFIX);
        Optional<String> locationArrivalIdOptional =
                param.getNamedValues(LOCATION_ARRIVAL_PREFIX);

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        Map<FlightLocationType, Location> locations = new HashMap<>();

        boolean hasFoundLocation;
        try {
            hasFoundLocation = addLocation(
                    locationDepartureIdOptional,
                    FlightLocationType.LOCATION_DEPARTURE,
                    locations
            ) || addLocation(
                    locationArrivalIdOptional,
                    FlightLocationType.LOCATION_ARRIVAL,
                    locations
            );
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        if (!hasFoundLocation) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        return new LinkFlightToLocationCommand(flight, locations);
    }
}
