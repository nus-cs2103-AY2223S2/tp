package wingman.logic.plane.unlinklocation;

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
import wingman.model.location.PlaneLocationType;
import wingman.model.plane.Plane;

/**
 * The command factory that unlinks pilots to locations.
 * Command factory is a class that can create an executable object, i.e.,
 * command class. The actual execution will be done by this object.
 */
public class UnlinkPlaneToLocationCommandFactory implements CommandFactory<UnlinkPlaneToLocationCommand> {
    private static final String COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String PLANE_PREFIX = "/pl";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered.\n"
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered.\n"
                    + "Please enter /pl followed by the plane ID.";
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;
    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     */
    public UnlinkPlaneToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public UnlinkPlaneToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getPlaneManager)
        );
    }

    /**
     * Creates a new link command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param planeManagerLazy    the lazy instance of the plane manager.
     */
    public UnlinkPlaneToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy
    ) {
        this.locationManagerLazy = locationManagerLazy;
        this.planeManagerLazy = planeManagerLazy;
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * and the location manager.
     *
     * @param locationManager the flight manager.
     * @param planeManager    the plane manager.
     */
    public UnlinkPlaneToLocationCommandFactory(
            ReadOnlyItemManager<Location> locationManager,
            ReadOnlyItemManager<Plane> planeManager
    ) {
        this(
                Lazy.of(locationManager),
                Lazy.of(planeManager)
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
                PLANE_PREFIX
        ));
    }

    private boolean addPlane(
            Optional<String> planeIdOptional,
            PlaneLocationType type,
            Map<PlaneLocationType, Plane> target
    ) throws CommandException {
        if (planeIdOptional.isEmpty()) {
            return false;
        }

        int planeId;
        try {
            planeId = Command.parseIntegerToZeroBasedIndex(planeIdOptional.get());
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    planeIdOptional.get()
            ));
        }

        boolean isPlaneIndexValid = (planeId < planeManagerLazy.get().size());
        if (!isPlaneIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    planeId + 1));
        }

        Optional<Plane> planeOptional = planeManagerLazy.get().getItemOptional(planeId);
        if (planeOptional.isEmpty()) {
            return false;
        }
        target.put(type, planeOptional.get());
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
    public UnlinkPlaneToLocationCommand createCommand(CommandParam param)
            throws ParseException, CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PLANE_PREFIX);

        Location location = getLocationOrThrow(locationIdOptional);
        Map<PlaneLocationType, Plane> plane = new HashMap<>();

        boolean hasFoundPilot;
        try {
            hasFoundPilot = addPlane(
                    pilotIdOptional,
                    PlaneLocationType.LOCATION_USING,
                    plane
            );
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        if (!hasFoundPilot) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        return new UnlinkPlaneToLocationCommand(location, plane);
    }
}
