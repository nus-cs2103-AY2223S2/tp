package seedu.address.logic.plane.unlinklocation;

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
import seedu.address.model.location.PlaneLocationType;
import seedu.address.model.plane.Plane;

/**
 * The command factory that unlinks pilots to locations.
 * Command factory is a class that can create an executable object, i.e.,
 * command class. The actual execution will be done by this object.
 */
public class UnlinkPlaneToLocationCommandFactory implements CommandFactory<UnlinkPlaneToLocationCommand> {
    private static final String COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/loc";
    private static final String PLANE_PREFIX = "/pl";

    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered. "
                    + "Please enter /pl followed by the plane iD.";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered. "
                    + "Please enter /loc followed by the location ID.";

    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy;

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

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
                modelLazy.map(Model::getPlaneManager),
                modelLazy.map(Model::getLocationManager)
        );
    }

    /**
     * Creates a new link command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param planeManagerLazy    the lazy instance of the plane manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public UnlinkPlaneToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy,
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy
    ) {
        this.planeManagerLazy = planeManagerLazy;
        this.locationManagerLazy = locationManagerLazy;
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * and the location manager.
     *
     * @param planeManager    the plane manager.
     * @param locationManager the flight manager.
     */
    public UnlinkPlaneToLocationCommandFactory(
            ReadOnlyItemManager<Plane> planeManager,
            ReadOnlyItemManager<Location> locationManager
    ) {
        this(
                Lazy.of(planeManager),
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
                PLANE_PREFIX
        ));
    }

    private boolean addPlane(
            Optional<String> pilotIdOptional,
            PlaneLocationType type,
            Map<PlaneLocationType, Plane> target
    ) {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPlane =
                Integer.parseInt(pilotIdOptional.get());
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
    ) throws ParseException {
        if (locationIdOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }
        int indexOfLocation =
                Integer.parseInt(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemOptional(indexOfLocation);
        if (locationOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        return locationOptional.get();
    }

    @Override
    public UnlinkPlaneToLocationCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PLANE_PREFIX);

        Map<PlaneLocationType, Plane> plane = new HashMap<>();

        boolean hasFoundPilot = addPlane(
                pilotIdOptional,
                PlaneLocationType.LOCATION_USING,
                plane
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        Location location = getLocationOrThrow(locationIdOptional);
        return new UnlinkPlaneToLocationCommand(plane, location);
    }
}
