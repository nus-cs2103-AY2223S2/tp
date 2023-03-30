package wingman.logic.pilot.unlinklocation;

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
import wingman.model.location.Location;
import wingman.model.location.PilotLocationType;
import wingman.model.pilot.Pilot;

/**
 * The command factory that unlinks pilots to locations.
 * Command factory is a class that can create an executable object, i.e.,
 * command class. The actual execution will be done by this object.
 */
public class UnlinkPilotToLocationCommandFactory implements CommandFactory<UnlinkPilotToLocationCommand> {
    private static final String COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String PILOT_PREFIX = "/pi";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered.\n"
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered.\n"
                    + "Please enter /pi followed by the pilot ID.";
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private final Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy;
    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
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
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getPilotManager)
        );
    }

    /**
     * Creates a new link command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param pilotManagerLazy    the lazy instance of the pilot manager.
     */
    public UnlinkPilotToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy
    ) {
        this.pilotManagerLazy = pilotManagerLazy;
        this.locationManagerLazy = locationManagerLazy;
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * and the location manager.
     *
     * @param locationManager the flight manager.
     * @param pilotManager    the pilot manager.
     */
    public UnlinkPilotToLocationCommandFactory(
            ReadOnlyItemManager<Location> locationManager,
            ReadOnlyItemManager<Pilot> pilotManager
    ) {
        this(
                Lazy.of(locationManager),
                Lazy.of(pilotManager)
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
    ) throws CommandException {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }

        int pilotId;
        try {
            pilotId = Command.parseIntegerToZeroBasedIndex(pilotIdOptional.get());
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    pilotIdOptional.get()
            ));
        }

        boolean isPilotIndexValid = (pilotId < pilotManagerLazy.get().size());
        if (!isPilotIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    pilotId + 1));
        }

        Optional<Pilot> pilotOptional = pilotManagerLazy.get().getItemOptional(pilotId);
        if (pilotOptional.isEmpty()) {
            return false;
        }
        target.put(type, pilotOptional.get());
        return true;
    }

    private Location getLocationOrThrow(
            Optional<String> locationIdOptional
    ) throws ParseException, CommandException {
        if (locationIdOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        int locationId;
        try {
            locationId = Command.parseIntegerToZeroBasedIndex(locationIdOptional.get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    locationIdOptional.get()
            ));
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
    public UnlinkPilotToLocationCommand createCommand(CommandParam param)
            throws ParseException, CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PILOT_PREFIX);

        Location location = getLocationOrThrow(locationIdOptional);
        Map<PilotLocationType, Pilot> pilot = new HashMap<>();

        boolean hasFoundPilot;
        try {
            hasFoundPilot = addPilot(
                    pilotIdOptional,
                    PilotLocationType.LOCATION_USING,
                    pilot
            );
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        if (!hasFoundPilot) {
            throw new ParseException(NO_PILOT_MESSAGE);
        }

        return new UnlinkPilotToLocationCommand(location, pilot);
    }
}
