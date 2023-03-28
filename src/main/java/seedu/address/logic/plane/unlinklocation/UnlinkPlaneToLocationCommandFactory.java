package seedu.address.logic.plane.unlinklocation;

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
import seedu.address.model.location.PlaneLocationType;
import seedu.address.model.plane.Plane;

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
            "No location has been entered. "
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered. "
                    + "Please enter /pl followed by the plane ID.";

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
            Optional<String> pilotIdOptional,
            PlaneLocationType type,
            Map<PlaneLocationType, Plane> target
    ) throws CommandException {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPlane =
                Command.parseIntegerToZeroBasedIndex(pilotIdOptional.get());
        Optional<Plane> planeOptional =
                planeManagerLazy.get().getItemOptional(indexOfPlane);
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
        int indexOfLocation =
                Command.parseIntegerToZeroBasedIndex(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemOptional(indexOfLocation);
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

        boolean hasFoundPilot = addPlane(
                pilotIdOptional,
                PlaneLocationType.LOCATION_USING,
                plane
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        return new UnlinkPlaneToLocationCommand(location, plane);
    }
}
