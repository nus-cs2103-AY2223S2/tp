package seedu.address.logic.crew.unlinklocation;

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
import seedu.address.model.crew.Crew;
import seedu.address.model.location.CrewLocationType;
import seedu.address.model.location.Location;

/**
 * The command factory that unlinks crews to locations.
 * Command factory is a class that can create an executable object, i.e.,
 * command class. The actual execution will be done by this object.
 */
public class UnlinkCrewToLocationCommandFactory implements CommandFactory<UnlinkCrewToLocationCommand> {
    private static final String COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/loc";
    private static final String CREW_PREFIX = "/crew";

    private static final String NO_CREW_MESSAGE =
            "No crew has been entered. "
                    + "Please enter /crew followed by the crew iD.";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered. "
                    + "Please enter /loc followed by the location ID.";

    private final Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy;

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     *
     */
    public UnlinkCrewToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public UnlinkCrewToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getCrewManager),
                modelLazy.map(Model::getLocationManager)
        );
    }

    /**
     * Creates a new link crew command factory with the given crew manager
     * lazy and the flight manager lazy.
     *
     * @param crewManagerLazy  the lazy instance of the crew manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public UnlinkCrewToLocationCommandFactory(
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
     * @param crewManager  the crew manager.
     * @param locationManager the flight manager.
     */
    public UnlinkCrewToLocationCommandFactory(
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

    private boolean addCrew(
            Optional<String> crewIdOptional,
            CrewLocationType type,
            Map<CrewLocationType, Crew> target
    ) {
        if (crewIdOptional.isEmpty()) {
            return false;
        }
        int indexOfCrew =
                Integer.parseInt(crewIdOptional.get());
        Optional<Crew> crewOptional =
                crewManagerLazy.get().getItemByIndex(indexOfCrew);
        if (crewOptional.isEmpty()) {
            return false;
        }
        target.put(type, crewOptional.get());
        return true;
    }

    private Location getLocationOrThrow(
            Optional<String> locationIdOptional
    ) throws ParseException {
        if (locationIdOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }
        int indexOfLocation =
                Integer.parseInt(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemByIndex(indexOfLocation);
        if (locationOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        return locationOptional.get();
    }

    @Override
    public UnlinkCrewToLocationCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> crewIdOptional =
                param.getNamedValues(CREW_PREFIX);

        Map<CrewLocationType, Crew> crews = new HashMap<>();

        boolean hasFoundCrew = addCrew(
                crewIdOptional,
                CrewLocationType.LOCATION_USING,
                crews
        );

        if (!hasFoundCrew) {
            throw new ParseException(NO_CREW_MESSAGE);
        }

        Location location = getLocationOrThrow(locationIdOptional);
        return new UnlinkCrewToLocationCommand(crews, location);
    }
}
