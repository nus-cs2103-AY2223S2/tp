package seedu.address.logic.pilot.linklocation;

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
import seedu.address.model.location.Location;
import seedu.address.model.location.PilotLocationType;
import seedu.address.model.pilot.Pilot;

/**
 * Links a pilot to location.
 * The location could be a place where they reside.
 * To link a pilot to a flight, the pilot should reside in
 * the same location as the flight's starting location.
 *
 */
public class LinkPilotToLocationCommandFactory implements CommandFactory<LinkPilotToLocationCommand> {
    private static final String COMMAND_WORD = "linklocation";
    private static final String LOCATION_PREFIX = "/loc";
    private static final String PILOT_PREFIX = "/pilot";

    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered. "
                    + "Please enter /pilot followed by the pilot iD.";

    private static final String NO_LOCATION_INPUT_MESSAGE =
            "No location has been entered. "
                    + "Please enter /loc followed by the location ID.";

    private static final String NO_LOCATION_FOUND_MESSAGE =
            "No location has been found in the list. "
                    + "Please enter /loc followed by the location ID.";

    private final Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy;

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     *
     */
    public LinkPilotToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public LinkPilotToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getPilotManager),
                modelLazy.map(Model::getLocationManager)
        );
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param pilotManagerLazy  the lazy instance of the pilot manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public LinkPilotToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy,
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy
    ) {
        this.pilotManagerLazy = pilotManagerLazy;
        this.locationManagerLazy = locationManagerLazy;
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * and the location manager.
     *
     * @param pilotManager  the pilot manager.
     * @param locationManager the flight manager.
     */
    public LinkPilotToLocationCommandFactory(
            ReadOnlyItemManager<Pilot> pilotManager,
            ReadOnlyItemManager<Location> locationManager
    ) {
        this(
                Lazy.of(pilotManager),
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
                PILOT_PREFIX
        ));
    }

    private boolean addPilot(
            Optional<String> pilotIdOptional,
            PilotLocationType type,
            Map<PilotLocationType, Pilot> target
    ) {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPilot =
                Integer.parseInt(pilotIdOptional.get());
        Optional<Pilot> pilotOptional =
                pilotManagerLazy.get().getItemByIndex(indexOfPilot);
        if (pilotOptional.isEmpty()) {
            return false;
        }
        target.put(type, pilotOptional.get());
        return true;
    }

    private Location getLocationOrThrow(
            Optional<String> locationIdOptional
    ) throws ParseException {
        if (locationIdOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_INPUT_MESSAGE);
        }
        int indexOfLocation =
                Integer.parseInt(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemByIndex(indexOfLocation);
        if (locationOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_FOUND_MESSAGE);
        }

        return locationOptional.get();
    }

    @Override
    public LinkPilotToLocationCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PILOT_PREFIX);

        Map<PilotLocationType, Pilot> pilot = new HashMap<>();

        boolean hasFoundPilot = addPilot(
                pilotIdOptional,
                PilotLocationType.LOCATION_USING,
                pilot
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PILOT_MESSAGE);
        }

        Location location = getLocationOrThrow(locationIdOptional);
        return new LinkPilotToLocationCommand(pilot, location);
    }
}
