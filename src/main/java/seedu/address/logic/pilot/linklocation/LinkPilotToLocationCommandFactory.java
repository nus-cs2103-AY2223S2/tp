package seedu.address.logic.pilot.linklocation;

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
import seedu.address.model.location.Location;
import seedu.address.model.location.PilotLocationType;
import seedu.address.model.pilot.Pilot;

/**
 * Links a pilot to location.
 * The location could be a place where they reside.
 * To link a pilot to a flight, the pilot should reside in
 * the same location as the flight's starting location.
 */
public class LinkPilotToLocationCommandFactory implements CommandFactory<LinkPilotToLocationCommand> {
    private static final String COMMAND_WORD = "linklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String PILOT_PREFIX = "/pi";

    private static final String NO_LOCATION_INPUT_MESSAGE =
            "No location has been entered. "
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_LOCATION_FOUND_MESSAGE =
            "No location has been found in the list. "
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered. "
                    + "Please enter /pi followed by the pilot ID.";

    private final Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy;
    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
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
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getPilotManager)
        );
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param pilotManagerLazy    the lazy instance of the pilot manager.
     */
    public LinkPilotToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy
    ) {
        this.locationManagerLazy = locationManagerLazy;
        this.pilotManagerLazy = pilotManagerLazy;
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * and the location manager.
     *
     * @param locationManager the flight manager.
     * @param pilotManager    the pilot manager.
     */
    public LinkPilotToLocationCommandFactory(
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
        int indexOfPilot =
                Command.parseIntegerToZeroBasedIndex(pilotIdOptional.get());
        Optional<Pilot> pilotOptional =
                pilotManagerLazy.get().getItemOptional(indexOfPilot);
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
            throw new ParseException(NO_LOCATION_INPUT_MESSAGE);
        }
        int indexOfLocation =
                Command.parseIntegerToZeroBasedIndex(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemOptional(indexOfLocation);
        if (locationOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_FOUND_MESSAGE);
        }

        return locationOptional.get();
    }

    @Override
    public LinkPilotToLocationCommand createCommand(CommandParam param)
            throws ParseException, CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PILOT_PREFIX);

        Location location = getLocationOrThrow(locationIdOptional);
        Map<PilotLocationType, Pilot> pilot = new HashMap<>();

        boolean hasFoundPilot = addPilot(
                pilotIdOptional,
                PilotLocationType.LOCATION_USING,
                pilot
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PILOT_MESSAGE);
        }

        return new LinkPilotToLocationCommand(location, pilot);
    }
}
