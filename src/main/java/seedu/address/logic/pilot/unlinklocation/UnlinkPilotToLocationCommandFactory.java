package seedu.address.logic.pilot.unlinklocation;

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
 * The command factory that unlinks pilots to locations.
 * Command factory is a class that can create an executable object, i.e.,
 * command class. The actual execution will be done by this object.
 */
public class UnlinkPilotToLocationCommandFactory implements CommandFactory<UnlinkPilotToLocationCommand> {
    private static final String COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/loc";
    private static final String PILOT_PREFIX = "/pilot";

    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered. "
                    + "Please enter /pilot followed by the pilot iD.";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered. "
                    + "Please enter /loc followed by the location ID.";

    private final Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy;

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     *
     */
    public UnlinkPilotToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public UnlinkPilotToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getPilotManager),
                modelLazy.map(Model::getLocationManager)
        );
    }

    /**
     * Creates a new link command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param pilotManagerLazy  the lazy instance of the pilot manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public UnlinkPilotToLocationCommandFactory(
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
    public UnlinkPilotToLocationCommandFactory(
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
    public UnlinkPilotToLocationCommand createCommand(CommandParam param) throws ParseException {
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
        return new UnlinkPilotToLocationCommand(pilot, location);
    }
}
