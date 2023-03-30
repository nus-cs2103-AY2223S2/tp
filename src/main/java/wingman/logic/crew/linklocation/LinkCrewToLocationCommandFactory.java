package wingman.logic.crew.linklocation;

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
import wingman.model.crew.Crew;
import wingman.model.location.CrewLocationType;
import wingman.model.location.Location;

/**
 * Links a crew to location.
 * The location could be a place where they reside.
 * To link a crew to a flight, the crew should reside in
 * the same location as the flight's starting location.
 */
public class LinkCrewToLocationCommandFactory implements CommandFactory<LinkCrewToLocationCommand> {
    private static final String COMMAND_WORD = "linklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String CREW_PREFIX = "/cr";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered.\n"
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_CREW_MESSAGE =
            "No crew has been entered.\n"
                    + "Please enter /cr followed by the crew ID.";
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private final Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy;
    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     */
    public LinkCrewToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public LinkCrewToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getCrewManager),
                modelLazy.map(Model::getLocationManager)
        );
    }

    /**
     * Creates a new link crew command factory with the given crew manager
     * lazy and the flight manager lazy.
     *
     * @param crewManagerLazy     the lazy instance of the crew manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public LinkCrewToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy,
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy
    ) {
        this.crewManagerLazy = crewManagerLazy;
        this.locationManagerLazy = locationManagerLazy;
    }

    /**
     * Creates a new link crew command factory with the given crew manager
     * and the location manager.
     *
     * @param crewManager     the crew manager.
     * @param locationManager the flight manager.
     */
    public LinkCrewToLocationCommandFactory(
            ReadOnlyItemManager<Crew> crewManager,
            ReadOnlyItemManager<Location> locationManager
    ) {
        this(
                Lazy.of(crewManager),
                Lazy.of(locationManager)
        );
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                LOCATION_PREFIX,
                CREW_PREFIX
        ));
    }

    private boolean addCrew(Optional<String> crewIdOptional, CrewLocationType type, Map<CrewLocationType, Crew> target)
            throws CommandException {
        if (crewIdOptional.isEmpty()) {
            return false;
        }

        int crewId;
        try {
            crewId =
                    Command.parseIntegerToZeroBasedIndex(crewIdOptional.get());
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    crewIdOptional.get()
            ));
        }

        boolean isCrewIndexValid = (crewId < crewManagerLazy.get().size());
        if (!isCrewIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    crewId + 1));
        }

        Optional<Crew> crewOptional = crewManagerLazy.get().getItemOptional(crewId);
        if (crewOptional.isEmpty()) {
            return false;
        }
        target.put(type, crewOptional.get());
        return true;
    }

    private Location getLocationOrThrow(Optional<String> locationIdOptional) throws ParseException, CommandException {
        if (locationIdOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        int locationId;
        try {
            locationId = Command.parseIntegerToZeroBasedIndex(locationIdOptional.get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(INVALID_INDEX_VALUE_MESSAGE, locationIdOptional.get()));
        }

        boolean isLocationIndexValid = (locationId < locationManagerLazy.get().size());
        if (!isLocationIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    locationId + 1));
        }

        Optional<Location> locationOptional = locationManagerLazy.get().getItemOptional(locationId);
        if (locationOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        return locationOptional.get();
    }

    @Override
    public LinkCrewToLocationCommand createCommand(CommandParam param) throws ParseException, CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> crewIdOptional =
                param.getNamedValues(CREW_PREFIX);

        Location location = getLocationOrThrow(locationIdOptional);
        Map<CrewLocationType, Crew> crews = new HashMap<>();

        boolean hasFoundCrew;
        try {
            hasFoundCrew = addCrew(
                    crewIdOptional,
                    CrewLocationType.LOCATION_USING,
                    crews
            );
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        if (!hasFoundCrew) {
            throw new ParseException(NO_CREW_MESSAGE);
        }

        return new LinkCrewToLocationCommand(location, crews);
    }
}
